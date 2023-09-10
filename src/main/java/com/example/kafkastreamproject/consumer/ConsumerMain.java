package com.example.kafkastreamproject.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class ConsumerMain {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(ConsumerMain.class, args);
  }
}
