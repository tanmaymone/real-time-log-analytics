package com.kafka.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.kafka.producer.model.Log;
import java.util.List;
import java.util.Random;

@Service
public class LogProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Random random = new Random();
    private final List<String> services = List.of("auth-service", "order-service", "payment-service");
    private final List<String> levels = List.of("INFO", "WARN", "ERROR");

    @Scheduled(fixedRate = 2000)
    public void sendLog() {
        Log log = new Log(
                services.get(random.nextInt(services.size())),
                levels.get(random.nextInt(levels.size())),
                "Something happened!",
                System.currentTimeMillis()
        );
        try {
            String json = new ObjectMapper().writeValueAsString(log);
            kafkaTemplate.send("web-logs", json);
            System.out.println("Sent log: " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
