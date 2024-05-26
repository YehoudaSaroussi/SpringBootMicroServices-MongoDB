package com.example.games_stats;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatRepository extends MongoRepository<GameStat, String> {
}
