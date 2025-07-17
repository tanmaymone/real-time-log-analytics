package com.kafka.consumer.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.kafka.consumer.entity.Log;

@Component
public class LogConsumer {

    @Autowired
    private LogRepository logRepo;

    @KafkaListener(topics = "web-logs", groupId = "log-group")
    public void consume(String message) throws JsonProcessingException {
        Log log = new ObjectMapper().readValue(message, Log.class);
        logRepo.save(log);
        System.out.println("Stored log: " + log);
    }
}

