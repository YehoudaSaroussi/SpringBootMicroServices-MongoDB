package com.example.players_stats;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.players.Player;

import lombok.Data;

@Document(collection = "playersStats")
@Data
public class PlayerStat {

    private Player player;
    private Team team;
    private Game game;
    private int points;
    private String pos;
    private String min;
    private int fgm;
    private int fga;
    private String fgp;
    private int ftm;
    private int fta;
    private String ftp;
    private int tpm;
    private int tpa;
    private String tpp;
    private int offReb;
    private int defReb;
    private int totReb;
    private int assists;
    private int pFouls;
    private int steals;
    private int turnovers;
    private int blocks;
    private String plusMinus;
    private String comment;

    public record Team(int id, String name, String nickname, String code, String logo) {
    }

    public record Game(int id, String date, String homeTeam, String visitorTeam, int homeTeamScore,
            int visitorTeamScore) {
    }

}
