package steps;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static utils.ErrorsMessage.errorMessage;
import static utils.SpecsForTests.librarySpec;

import entity.Author;
import entity.Book;
import entity.Customer;
import io.qameta.allure.Step;
import java.util.List;
import model.responseModel.GetAuthorAndUsersBooksResponse;

public class GetAuthorBooksSteps {

  @Step("Получение книг автора по id")
  public List<GetAuthorAndUsersBooksResponse> getAuthorsBooks(Long id) {
    return given()
      .spec(librarySpec)
      .when()
      .get(format("authors/%s/books", id))
      .then()
      .log().body()
      .extract().jsonPath().getList("", GetAuthorAndUsersBooksResponse.class);
  }
}
