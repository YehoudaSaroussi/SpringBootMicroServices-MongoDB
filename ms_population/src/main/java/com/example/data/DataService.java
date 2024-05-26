package com.example.data;

// import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
// import java.util.Collections;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.populations.*;

@Service
@Slf4j
public class DataService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String rapidApiKey = "Your_Rapid_API_Key";
    private final String rapidApiHost = "get-population.p.rapidapi.com";

    public DataService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> T fetchDataFromAPI(String url, String country, Class<T> responseType) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", rapidApiHost);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + "?country=" + country,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                String responseBody = responseEntity.getBody();
                // JsonNode responseNode = objectMapper.readTree(responseBody);
                if (responseBody != null) {
                    return objectMapper.readValue(responseBody,
                            responseType);
                } else {
                    // Handle empty or missing response
                    throw new RuntimeException("Response body is null");
                }
            } catch (IOException e) {
                // Handle JSON parsing exception
                log.trace("Failed to parse JSON response from the API.", e);
                throw new RuntimeException("Failed to fetch data from the API.", e);
            }
        } else {
            // Handle HTTP error response
            log.error("Failed to fetch data from the API. HTTP status code: " + responseEntity.getStatusCode());
            throw new RuntimeException(
                    "Failed to fetch data from the API. HTTP status code: " + responseEntity.getStatusCode());
        }
    }

    public <T, ID> T fetchAndSaveData(String url, String country, Class<T> responseType,
            MongoRepository<T, ID> repository) {

        T data = fetchDataFromAPI(url, country, responseType);

        if (data != null) {

            saveDataToMongoDB(data, repository);

            return data;
        } else {
            log.error("Failed to fetch data from the API.");
            throw new RuntimeException("Failed to fetch data from the API.");
        }
    }

    public <T, ID> void saveDataToMongoDB(T data, MongoRepository<T, ID> repository) {

        if (data == null) {
            log.error("Data is null. Cannot save data to the database.");
            return;
        }

        if (data instanceof Population) {
            Population population = (Population) data;
            List<Population> popList = ((PopulationRepository) repository)
                    .findByCountry(population.getCountry());
            Population existingPopulation = popList.isEmpty() ? null : popList.get(0);
            if (existingPopulation != null) {
                population.setId(existingPopulation.getId());
            }
            repository.save(data);

        }

    }
}
