package steps;

import entity.Book;
import io.qameta.allure.Step;
import model.requestModel.BookSaveRequest;
import model.responseModel.BookSaveResponse;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.ErrorsMessage.errorMessage;
import static utils.SpecsForTests.librarySpec;

public class BookSaveSteps {

  @Step("Отправляем POST /book/save запрос. Добавление книги")
  public BookSaveResponse postBookSave(BookSaveRequest body, int expectedStatusCode) {
    return given()
      .spec(librarySpec)
      .body(body)
      .when()
      .post("books/save")
      .then()
      .statusCode(expectedStatusCode)
      .extract().as(BookSaveResponse.class);
  }

  @Step("Проверка ответа запроса на сохранение книги")
  public void bookSaveResponseShouldBeCorrect(BookSaveResponse response) {
    assertTrue(response.getBookId() > 0, "bookId должно быть больше 0.");
  }

  @Step("Проверка данных сохранённой книги")
  public void bookDataShouldBeAsInRequest(Book book, BookSaveRequest request) {
    assertEquals(request.getBookTitle(), book.getBookTitle(), errorMessage("bookTitle"));
    assertEquals(request.getAuthor().getId(), book.getAuthorId(), errorMessage("authorId"));
    if (request.getCustomer() != null) {
      assertEquals(request.getCustomer().getId(), book.getCustomerId(),
        errorMessage("customerId"));
    }
  }
}
