package com.example.games_stats;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "gamesStats")
@Data
public class GameStat {

    private Team team;
    private List<Statistics> statistics;

    public record Team(
            int id,
            String name,
            String nickname,
            String code,
            String logo) {
    }

    public record Statistics(
            int fastBreakPoints,
            int pointsInPaint,
            int biggestLead,
            int secondChancePoints,
            int pointsOffTurnovers,
            int longestRun,
            int points,
            int fgm,
            int fga,
            String fgp,
            int ftm,
            int fta,
            String ftp,
            int tpm,
            int tpa,
            String tpp,
            int offReb,
            int defReb,
            int totReb,
            int assists,
            int pFouls,
            int steals,
            int turnovers,
            int blocks,
            String plusMinus,
            String min) {
    }
}
