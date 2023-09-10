package com.example.kafkastreamproject.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class ProducerMain {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(ProducerMain.class, args);

    log.info("Retrieving Producer Bean in main class");
    StringMessageProducer producer = ctx.getBean(StringMessageProducer.class);
    for (long i = 11; i <= 20; i++) {
      String stringMessage =  Long.toString(i);
      producer.sendMessage(stringMessage);
    }
  }

}
