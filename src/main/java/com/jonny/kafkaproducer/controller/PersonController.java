package com.jonny.kafkaproducer.controller;

import com.jonny.kafkaproducer.api.Person;
import com.jonny.kafkaproducer.producer.PersonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  @Autowired
  private PersonProducer personProducer;

  @PostMapping(
      path = "/person",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  public void createPerson(@RequestBody Person person) {
    personProducer.produce(person);
  }
}
