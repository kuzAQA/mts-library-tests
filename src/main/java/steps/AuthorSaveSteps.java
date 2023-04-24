package steps;

import entity.Author;
import io.qameta.allure.Step;
import model.requestModel.AuthorSaveRequest;
import model.responseModel.AuthorSaveResponse;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.ErrorsMessage.errorMessage;
import static utils.SpecsForTests.librarySpec;

public class AuthorSaveSteps {

  @Step("Отправляем POST /authors/save запрос. Создание автора")
  public AuthorSaveResponse postAuthorSave(AuthorSaveRequest body, int expectedStatusCode) {
    return given()
      .spec(librarySpec)
      .body(body)
      .when()
      .post("authors/save")
      .then()
      .statusCode(expectedStatusCode)
      .extract().as(AuthorSaveResponse.class);
  }

  @Step("Проверка ответа запроса на сохранение автора")
  public void authorSaveResponseShouldBeCorrect(AuthorSaveResponse response) {
    assertTrue(response.getAuthorId() > 0, "authorId должно быть больше 0.");
  }

  @Step("Проверка данных сохранённого автора")
  public void authorDataShouldBeAsInRequest(Author author, AuthorSaveRequest request) {
    assertEquals(request.getFirstName(), author.getFirstName(), errorMessage("firstMame"));
    assertEquals(request.getSecondName(), author.getSecondName(), errorMessage("secondName"));
  }
}
