package com.example.games;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "games")
@Data
public class Game {
    @Id
    private String id;
    private String league;
    private int season;
    private DateObject date;
    private int stage;
    private StatusObject status;
    private PeriodsObject periods;
    private ArenaObject arena;
    private TeamsObject teams;
    private ScoresObject scores;
    private List<String> officials;
    private int timesTied;
    private int leadChanges;
    private String nugget;

    public record DateObject(String start, String end, String duration) {
    }

    public record StatusObject(String clock, boolean halftime, int shortDuration, String longDescription) {
    }

    public record PeriodsObject(int current, int total, boolean endOfPeriod) {
    }

    public record ArenaObject(String name, String city, String state, String country) {
    }

    public record TeamsObject(TeamObject visitors, TeamObject home) {
    }

    public record TeamObject(int id, String name, String nickname, String code, String logo) {
    }

    public record ScoresObject(TeamScoresObject visitors, TeamScoresObject home) {
    }

    public record TeamScoresObject(int win, int loss, SeriesObject series, List<String> linescore, int points) {
    }

    public record SeriesObject(int win, int loss) {
    }

}
