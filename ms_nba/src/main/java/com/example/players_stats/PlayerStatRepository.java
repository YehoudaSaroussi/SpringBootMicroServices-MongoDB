package com.example.players_stats;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatRepository extends MongoRepository<PlayerStat, String> {
}
