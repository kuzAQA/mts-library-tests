package utils;

import static io.restassured.RestAssured.with;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecsForTests {

  public static RequestSpecification librarySpec = with()
      .baseUri("http://localhost/")
      .basePath("library/")
      .port(8080)
      .contentType(ContentType.JSON)
      .log().uri()
      .log().body();
}
