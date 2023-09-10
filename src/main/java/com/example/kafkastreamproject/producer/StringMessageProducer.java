package com.example.kafkastreamproject.producer;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringMessageProducer {

  KafkaTemplate<String, String> kafkaTemplate;

  @Value("${string-stream.topic-name}")
  private String topicName;

  public StringMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String stringMessage) {
    ProducerRecord producerRecord = new ProducerRecord<>(topicName,
        stringMessage,
        stringMessage);

    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(
        producerRecord);
    ;
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        log.info("Sent message={} with offset={}", stringMessage,
            result.getRecordMetadata().offset());
      } else {
        log.error("Unable to send message={} due to : {}", stringMessage, ex.getMessage(), ex);
      }
    });
  }
}
