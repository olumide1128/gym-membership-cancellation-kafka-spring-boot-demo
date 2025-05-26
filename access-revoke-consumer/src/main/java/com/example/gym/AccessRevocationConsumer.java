package com.example.gym;

import com.example.gym.model.MembershipCancellationEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccessRevocationConsumer {
    @KafkaListener(topics = "cancel_membership", groupId = "access-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleCancellation(ConsumerRecord<String, MembershipCancellationEvent> record) {

        int randomNumber = new Random().nextInt(10) + 1;  // ✅ Random number between 1 and 10
        System.out.println("Processing event: " + record.value() + " | Generated Number: " + randomNumber);

        if (randomNumber % 2 != 0) {  // ✅ If the number is odd, trigger an error
            throw new RuntimeException("Simulated failure - odd number detected: " + randomNumber);
        }

        MembershipCancellationEvent event = record.value();
        System.out.println("Revoking access for member: " + event.getMemberName());
    }
}


