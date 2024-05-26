package com.example.data;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.populations.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/population")
public class DataController {

    private final PopulationRepository populationRepository;

    public DataController(PopulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }

    @GetMapping("/country")
    public List<Population> getPopulation() {
        log.info("Getting population from DB");
        return populationRepository.findAll();
    }

    @GetMapping("/country/{country}")
    public List<Population> getPopulationByCountry(@PathVariable String country) {
        return populationRepository.findByCountry(country);
    }

}
