# Gym-membership-cancellation-kafka-spring-boot-demo
This is a Kafka based Spring boot Project to show how Multiple Independent Consumers can consume from a single topic.

This is a Multi Module Gradle Proect with Error Handling (Retries and DLT) in the consumers.

## Proect Structure

There is one producer and 3 Consumers in this proect. 
- cancel-membership-producer: Sends a MembershipCancellationEvent message to a topic (cancel-membership)
- access-revoke-consumer/notification-consumer/billing-consumer: Each of these Consumers independently consumes off the topic
- common: This is where common consumer kafka configuration/properties are placed

## Instruction to run application

- Clone this Repo
- A Kafka Server would be needed for this Project, you would need a workin Kafka Server which is exposed on http://localhost:29092.
- I have added a docker-compose.yaml in the root directory which you can run using the command "docker-compose up -d" in your terminal and you have a working kafka server
- Start the cancel-membership-producer application
- Start all consumer applications.
- An Endpoint has been exposed in the producer controller to send a message to the topic. *POST: http://localhost:8080/membership/cancel/{memberId}*
- The memberId can be any number from 1 to 5. A different member would be retrieved based on the number.
- Once the api call is successful, you get a message that the cancellation event has been sent to teh topic
- Now, you should see that each consumer independently consumes the message when you check the terminal

## Error Handling

To simulate an excpetion to see how the error handling and retry works, in each consumer service, after consuming a mesage from the topic, a random number
between 1 and 10 is generated, if it is an odd number, a runtime exception is thrown. Based on the DefualtErrorHandler configuation, there would be 3 retries,
if an even number gets generated, the message is processed, but if an odd number is generated 3 times, then the message is sent to a DLT (cancel-membership-dlt)
where it can be investigated manually.
