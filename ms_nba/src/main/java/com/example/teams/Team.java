package com.example.teams;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "teams")
@Data
public class Team {
    @Id
    private String id;
    private String name;
    private String nickname;
    private String code;
    private String city;
    private String logo;
    private boolean allStar;
    private boolean nbaFranchise;
    private Map<String, LeagueInfo> leagues;

    public record LeagueInfo(String conference, String division) {
    }

}
