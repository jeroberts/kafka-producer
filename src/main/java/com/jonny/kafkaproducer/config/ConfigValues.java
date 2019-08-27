package com.jonny.kafkaproducer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigValues {

  @Value("${kafka.servers}")
  private String kafkaServers;

  @Value("${kafka.topic.person}")
  private String personTopic;

  public String getKafkaServers() {
    return kafkaServers;
  }

  public String getPersonTopic() {
    return personTopic;
  }
}
