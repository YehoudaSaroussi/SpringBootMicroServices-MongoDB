package com.example.players;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "players")
@Data
public class Player {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private Birth birth;
    private NBA nba;
    private Height height;
    private Weight weight;
    private String college;
    private String affiliation;
    private Leagues leagues;

    public record Birth(String date, String country) {
    }

    public record NBA(int start, int pro) {
    }

    public record Height(String feets, String inches, String meters) {
    }

    public record Weight(String pounds, String kilograms) {
    }

    public record Leagues(Standard standard) {
    }

    public record Standard(Integer jersey, Boolean active, String pos) {
    }

}
