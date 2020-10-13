package com.intuit.app.models;

public class User {
  private String firstName;
  private String lastName;
  private String userId;

  public User(String firstName, String lastName, String userId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userId = userId;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUserId() {
    return userId;
  }
}
