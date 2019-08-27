package com.jonny.kafkaproducer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonny.kafkaproducer.api.Person;
import com.jonny.kafkaproducer.producer.PersonProducer;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ContextConfiguration(classes = PersonController_IT.TestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonController_IT {

  @Autowired private WebApplicationContext webApplicationContext;
  @Autowired private PersonProducer personProducer;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper = new ObjectMapper();

  private EasyRandom easyRandom = new EasyRandom();

  @Configuration
  @ComponentScan("com.jonny.kafka-producer.controller")
  @EnableWebMvc
  public static class TestConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public PersonProducer personProducer() {
      return mock(PersonProducer.class);
    }
  }

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    reset(this.personProducer);
  }

  @Test
  public void createPerson() throws Exception {
    // Given
    Person person = easyRandom.nextObject(Person.class);
    String bodyString = objectMapper.writeValueAsString(person);

    // When
    mockMvc.perform(
            post("/person")
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .content(bodyString))
            .andExpect(status().isCreated());

    // Then
    verify(personProducer).produce(person);
  }
}