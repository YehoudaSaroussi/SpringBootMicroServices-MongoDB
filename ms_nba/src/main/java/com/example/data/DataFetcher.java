package com.example.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.games.*;
import com.example.games_stats.*;
import com.example.players.*;
import com.example.players_stats.*;
import com.example.teams.*;
import com.example.teams_stats.*;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataFetcher implements CommandLineRunner {

    @Autowired
    private DataService dataService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameStatRepository gameStatRepository;

    @Autowired
    private TeamStatRepository teamStatRepository;

    @Autowired
    private PlayerStatRepository playerStatRepository;

    @Override
    public void run(String... args) throws Exception {
        fetchAndSaveGames();
        // fetchAndSaveTeams();
        // fetchAndSavePlayers();
        // fetchAndSaveGamesStats();
        // fetchAndSaveTeamStats();
        // fetchAndSavePlayerStats();
    }

    private void fetchAndSaveGames() {
        String url = "https://api-nba-v1.p.rapidapi.com/games?date=2022-02-12";
        log.info("Fetching games data for : " + url);
        dataService.fetchAndSaveData(url, Game.class, gameRepository);
    }

    private void fetchAndSaveTeams() {
        String url = "https://api-nba-v1.p.rapidapi.com/teams";
        log.info("Fetching teams data for : " + url);
        dataService.fetchAndSaveData(url, Team.class, teamRepository);
    }

    private void fetchAndSavePlayers() {
        String url = "https://api-nba-v1.p.rapidapi.com/players?season=2021&team=1";
        log.info("Fetching players data for : " + url);
        dataService.fetchAndSaveData(url, Player.class, playerRepository);
    }

    private void fetchAndSaveGamesStats() {
        String url = "https://api-nba-v1.p.rapidapi.com/games/statistics?id=10403";
        log.info("Fetching games stats data for : " + url);
        dataService.fetchAndSaveData(url, GameStat.class, gameStatRepository);
    }

    private void fetchAndSaveTeamStats() {
        String url = "https://api-nba-v1.p.rapidapi.com/teams/statistics?id=1&season=2020";
        log.info("Fetching team stats data for : " + url);
        dataService.fetchAndSaveData(url, TeamStat.class, teamStatRepository);
    }

    private void fetchAndSavePlayerStats() {
        String url = "https://api-nba-v1.p.rapidapi.com/players/statistics?game=8133";
        log.info("Fetching player stats data for : " + url);
        dataService.fetchAndSaveData(url, PlayerStat.class, playerStatRepository);
    }
}
