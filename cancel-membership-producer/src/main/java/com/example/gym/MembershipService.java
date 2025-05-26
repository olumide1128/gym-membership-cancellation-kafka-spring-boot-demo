package com.example.gym;

import com.example.gym.model.MembershipCancellationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class MembershipService {

    @Autowired
    @Qualifier("eventKafkaTemplate")
    KafkaTemplate<String, MembershipCancellationEvent> kafkaTemplate;

    private static final Map<Integer, String> MEMBER_DATA = Map.of(
            1, "Alice Johnson",
            2, "Bob Smith",
            3, "Charlie Brown",
            4, "Diana Carter",
            5, "Ethan Williams"
    );

    public String cancelMembership(String memberId) {
//        log.info("Processing membership cancellation for ID: {}", memberId);

        if(MEMBER_DATA.containsKey(Integer.parseInt(memberId))){
            // Create the cancellation event
            MembershipCancellationEvent event = new MembershipCancellationEvent(memberId, MEMBER_DATA.get(Integer.parseInt(memberId)));

            // Send event to Kafka
//        log.info("Sending event to Kafka for member: {}", event.getMemberName());
            kafkaTemplate.send("cancel_membership", memberId, event);
            return "Cancellation event sent for member: " + event.getMemberName();
        }

        return "404 - User not found";
    }

}
