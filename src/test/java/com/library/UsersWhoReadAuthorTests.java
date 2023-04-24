package com.library;

import static io.restassured.RestAssured.given;
import static utils.AssertsBookList.booksListResponseShouldBeCorrect;

import entity.Author;
import entity.Book;
import entity.Customer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.requestModel.GetBooksRequest;
import model.responseModel.GetAuthorAndUsersBooksResponse;
import model.responseModel.ListBooksId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.GetBooksSteps;
import steps.GetUsersWhoReadAuthorSteps;

@Feature("Получение списка пользователей, которые читают выбранного автора")
@Link("/authors/{userId}/users")
@Story("GET /authors/{userId}/users. Получения списка пользователей по автору.")
public class UsersWhoReadAuthorTests extends TestBase {

  GetUsersWhoReadAuthorSteps getUsersWhoReadAuthorSteps = new GetUsersWhoReadAuthorSteps();

  @Test
  @DisplayName("Позитивный тест /authors/{customerId}/users")
  @Description("Проверка успешного ответа со списком книг автора")
  public void successAuthorBooks() {
    Author author = libraryFixtureSteps.insertAuthor("Джошуа", "Блох");
    Customer customer = libraryFixtureSteps.insertCustomer("Николай", "Владимирович");
    Book book = libraryFixtureSteps.insertBook("Java. Эффективное программирование",
      author.getId());

    //временный костыль, позднее перекочует в отдельный метод для проверки выдачи книги
    GetBooksSteps getBooksSteps = new GetBooksSteps();
    List<Long> booksList = Arrays.asList(book.getId());
    GetBooksRequest request = new GetBooksRequest().setBooksId(booksList);
    getBooksSteps.getBooks(customer.getId(), request);

    List<GetAuthorAndUsersBooksResponse> responseList = getUsersWhoReadAuthorSteps.getUsersWhoReadAuthors(
      author.getId());
    booksListResponseShouldBeCorrect(responseList, book, author, customer);
  }
}
