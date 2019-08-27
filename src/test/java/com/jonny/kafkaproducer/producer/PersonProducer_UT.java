package com.jonny.kafkaproducer.producer;


import com.jonny.kafkaproducer.api.Person;
import com.jonny.kafkaproducer.config.ConfigValues;
import org.jeasy.random.EasyRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonProducer_UT {

  @InjectMocks
  private PersonProducer testObject;

  @Mock
  private KafkaTemplate<String, Person> mockPersonKafkaTemplate;
  @Mock
  private ConfigValues mockConfigValues;

  private EasyRandom easyRandom = new EasyRandom();

  @Test
  public void produce() {
    // Given
    Person person = easyRandom.nextObject(Person.class);

    String personTopic = easyRandom.nextObject(String.class);
    when(mockConfigValues.getPersonTopic()).thenReturn(personTopic);

    // When
    testObject.produce(person);

    // Then
    verify(mockPersonKafkaTemplate).send(personTopic, person);
  }
}