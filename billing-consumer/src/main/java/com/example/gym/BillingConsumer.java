package com.example.gym;

import com.example.gym.model.MembershipCancellationEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BillingConsumer {
    @KafkaListener(topics = "cancel_membership", groupId = "billing-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleCancellation(ConsumerRecord<String, MembershipCancellationEvent> record) {
        MembershipCancellationEvent event = record.value();
        System.out.println("Revoking access for member: " + event.getMemberName());
    }
}


