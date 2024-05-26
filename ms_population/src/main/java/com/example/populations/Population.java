package com.example.populations;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "population")
public class Population {

    @Id
    private String id;
    private int count;
    private String readable_format;
    private String country;

}
