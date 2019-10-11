package com.jonny.kafkaproducer.producer;

import com.jonny.kafkaproducer.api.Person;
import com.jonny.kafkaproducer.config.ConfigValues;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonProducer {

  private static Logger LOG = LogManager.getLogger();

  @Autowired
  private KafkaTemplate<String, Person> personKafkaTemplate;
  @Autowired
  private ConfigValues configValues;

  public void produce(Person person) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("===================================================================\n");
    stringBuilder.append("Published new Person - ");
    stringBuilder.append(person.getName());
    stringBuilder.append(" - to Topic - ");
    stringBuilder.append(configValues.getPersonTopic());
    stringBuilder.append("\n===================================================================");
    LOG.info(stringBuilder.toString());

    personKafkaTemplate.send(configValues.getPersonTopic(), person);
  }
}
