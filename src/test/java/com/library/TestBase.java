package com.library;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import repository.LibraryFixtureSteps;
import io.qameta.allure.restassured.AllureRestAssured;

public class TestBase {
  LibraryFixtureSteps libraryFixtureSteps;

  @BeforeEach
  void setupDatabase() {
    libraryFixtureSteps = new LibraryFixtureSteps();
  }

  @AfterEach
  void closeSession() {
    libraryFixtureSteps.closeSession();
  }

  @BeforeEach
  public void restAssuredLogs() {
    RestAssured.filters(
      new ResponseLoggingFilter(),
      new AllureRestAssured()
    );
  }

//  @AfterEach
//  public void resetRestAssured() {
//    RestAssured.reset();
//  }
}
