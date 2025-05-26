package com.example.gym.utils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaErrorUtil {

    @Autowired
    @Qualifier("stringKafkaTemplate")
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendToDLT(String dltTopic, ConsumerRecord<?, ?> record, Exception exception) {
        String message = "Failed to process: " + record.value() + " | Error: " + exception.getMessage();
        kafkaTemplate.send(dltTopic, record.key().toString(), message);
        System.err.println("Sent to DLT: " + message);
    }

}
