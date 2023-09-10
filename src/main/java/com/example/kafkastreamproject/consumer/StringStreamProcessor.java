package com.example.kafkastreamproject.consumer;

import java.util.Collections;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringStreamProcessor {

  @Value("${kafka.schema.registry.url}")
  private String schemaRegistryUrl;

  @Value("${string-stream.topic-name}")
  private String topicName;

  @Autowired
  public Topology buildTopology(StreamsBuilder streamsBuilder) {
    Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url",
        schemaRegistryUrl);

    var stringStream = streamsBuilder.stream(topicName,
        Consumed.with(Serdes.String(), Serdes.String()));

    stringStream
        .peek((key, value) -> {
          log.info("Processing key:{}, value:{}", key, value);
        })
        .foreach((key, value) -> {
          Long num = Long.valueOf(value);
          if (num % 15 == 0) {
            throw new RuntimeException("Mocking Exception on [num % 5] while processing");
          }
        })
    ;
    return streamsBuilder.build();
  }
}
