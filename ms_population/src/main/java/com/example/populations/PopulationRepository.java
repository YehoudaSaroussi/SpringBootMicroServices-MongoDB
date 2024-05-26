package com.example.populations;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends MongoRepository<Population, String> {

    List<Population> findByCountry(String country);
}
