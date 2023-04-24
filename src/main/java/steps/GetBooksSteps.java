package steps;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static utils.SpecsForTests.librarySpec;

import io.qameta.allure.Step;
import java.util.List;
import model.requestModel.GetBooksRequest;
import model.responseModel.GetBooksResponse;

public class GetBooksSteps {

  @Step("Выдача книги читателю")
  public GetBooksResponse getBooks(Long customerId, GetBooksRequest body) {
    return given()
      .spec(librarySpec)
      .body(body)
      .when()
      .post(format("users/%s/getBooks", customerId))
      .then()
      .log().body()
      .extract().as(GetBooksResponse.class);
  }
}