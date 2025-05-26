package com.example.gym.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic membershipCancellationTopic() {
        return new NewTopic("cancel_membership", 1, (short) 1);
    }

    @Bean
    public NewTopic membershipCancellationDltTopic() {
        return new NewTopic("membership-cancellation-dlt", 1, (short) 1);
    }
}
