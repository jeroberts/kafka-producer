package com.jonny.kafkaproducer.api;

public class Person {

  private String name;
  private String dateOfBirth;
  private String email;

  private Person() {}

  public Person(String name, String dateOfBirth, String email) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public String getEmail() {
    return email;
  }
}
