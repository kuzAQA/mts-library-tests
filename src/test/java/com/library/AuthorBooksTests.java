package com.library;

import static utils.AssertsBookList.booksListResponseShouldBeCorrect;

import entity.Author;
import entity.Book;
import entity.Customer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import java.util.List;
import model.responseModel.GetAuthorAndUsersBooksResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.GetAuthorBooksSteps;

@Feature("Получение списка книг по автору")
@Link("/authors/{authorId}/books")
@Story("GET /authors/{authorId}/books. Получения списка книг по автору.")
public class AuthorBooksTests extends TestBase {

  GetAuthorBooksSteps getAuthorBooksSteps = new GetAuthorBooksSteps();


  @Test
  @DisplayName("Позитивный тест /authors/{authorId}/books")
  @Description("Проверка успешного ответа со списком книг автора")
  public void successAuthorBooks() {
    Author author = libraryFixtureSteps.insertAuthor("Джошуа", "Блох");
    Book book = libraryFixtureSteps.insertBook("Java. Эффективное программирование", author.getId());

    List<GetAuthorAndUsersBooksResponse> responseList = getAuthorBooksSteps.getAuthorsBooks(author.getId());
    booksListResponseShouldBeCorrect(responseList, book, author, new Customer());
  }
}
