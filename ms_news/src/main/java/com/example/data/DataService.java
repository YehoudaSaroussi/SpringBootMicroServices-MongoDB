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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
// change all the prints in logs
public class DataService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private String gnewsApiKey = "Your_API_Key";

    public DataService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> List<T> fetchDataFromAPI(String url, Class<T> responseType) {

        String urlWithApi = url + "&apikey=" + gnewsApiKey;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlWithApi, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                String responseBody = responseEntity.getBody();
                JsonNode responseNode = objectMapper.readTree(responseBody).get("articles");
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

            log.info("Number of items fetched: " + data.size());

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
