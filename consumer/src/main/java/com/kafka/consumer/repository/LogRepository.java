package com.kafka.consumer.repository;

import com.kafka.consumer.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
}
