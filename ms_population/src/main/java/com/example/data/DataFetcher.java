package com.example.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.populations.*;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataFetcher implements CommandLineRunner {

    @Autowired
    private DataService dataService;
    @Autowired
    private PopulationRepository populationRepository;

    @Override
    public void run(String... args) throws Exception {
        fetchAndSavePopulations("Egypt");
    }

    private void fetchAndSavePopulations(String country) {
        String url = "https://get-population.p.rapidapi.com/population/country";
        log.info("Fetching population data for :", country);
        dataService.fetchAndSaveData(url, country, Population.class, populationRepository);
    }

}
