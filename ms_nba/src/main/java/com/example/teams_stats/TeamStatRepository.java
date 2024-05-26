package com.example.teams_stats;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamStatRepository extends MongoRepository<TeamStat, String> {
}
