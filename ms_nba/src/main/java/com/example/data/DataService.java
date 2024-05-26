package com.example.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class DataService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String RapidAPI_Key = "Your_Rapid_API_Key";
    private final String RapidAPI_Host = "api-nba-v1.p.rapidapi.com";

    public DataService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> List<T> fetchDataFromAPI(String url, Class<T> responseType) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", RapidAPI_Key);
        headers.set("X-RapidAPI-Host", RapidAPI_Host);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                String responseBody = responseEntity.getBody();
                JsonNode responseNode = objectMapper.readTree(responseBody).get("response");
                if (responseNode != null) {
                    return objectMapper.readValue(responseNode.traverse(),
                            objectMapper.getTypeFactory().constructCollectionType(List.class, responseType));
                } else {
                    // Handle empty or missing response
                    return Collections.emptyList();
                }
            } catch (IOException e) {
                // Handle JSON parsing exception
                log.trace("Failed to parse JSON response from the API.", e);
                return Collections.emptyList();
            }
        } else {
            // Handle HTTP error response
            return Collections.emptyList();
        }
    }

    public <T, ID> List<T> fetchAndSaveData(String url, Class<T> responseType,
            MongoRepository<T, ID> repository) {

        List<T> data = fetchDataFromAPI(url, responseType);

        if (!data.isEmpty()) {

            log.trace("Number of items fetched: " + data.size());

            saveDataToMongoDB(data, repository);

            return data;
        } else {
            log.error("Failed to fetch data from the API. HTTP status code: " + HttpStatus.NOT_FOUND);
            throw new RuntimeException("Failed to fetch data from the API.");
        }
    }

    public <T, ID> void saveDataToMongoDB(List<T> entities, MongoRepository<T, ID> repository) {
        if (entities != null) {
            repository.saveAll(entities);
        } else {
            log.error("Entities list is null.");
        }
    }

}
