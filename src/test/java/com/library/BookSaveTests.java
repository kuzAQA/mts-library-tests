package com.library;

import entity.Author;
import entity.Book;
import model.requestModel.AuthorForBook;
import model.requestModel.BookSaveRequest;
import model.responseModel.BookSaveResponse;
import org.junit.jupiter.api.Disabled;
import steps.BookSaveSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Feature("Добавление книги")
@Link("/books/save")
@Story("POST /books/save. Добавление новой книги.")
public class BookSaveTests extends TestBase {

  private final BookSaveSteps bookSaveSteps = new BookSaveSteps();

  @Test
  @DisplayName("Позитивный тест /books/save")
  @Description("Проверка успешного добавления книги, с корректными данными.")
  public void successBookSave() {
    Author author = libraryFixtureSteps.insertAuthor("Джошуа", "Блох");

    BookSaveRequest request = new BookSaveRequest()
      .setBookTitle("Java. Эффективное программирование")
      .setAuthor(new AuthorForBook(author.getId()));

    BookSaveResponse response = bookSaveSteps.postBookSave(request, 200);
    bookSaveSteps.bookSaveResponseShouldBeCorrect(response);

    Book savedBook = libraryFixtureSteps.findBookById(response.getBookId());
    bookSaveSteps.bookDataShouldBeAsInRequest(savedBook, request);
  }

  @DisplayName("Негативный тест /books/save")
  @Description("Проверка ошибки при сохраниении книги без автора")
  @Test
  void failureBookSaveWithMissingAuthor() {
    BookSaveRequest request = new BookSaveRequest()
      .setBookTitle("Java. Эффективное программирование");

    bookSaveSteps.postBookSave(request, 500);
  }

  @Disabled("Требуется доработка API. Запрещено создавать книги с пустым названием.")
  @DisplayName("Негативный тест /books/save")
  @Description("Проверка ошибки при сохраниении книги с пустым названием.")
  @ParameterizedTest(name = "{displayName} [{index}] Параметры: bookTitle=[{0}].")
  @ValueSource(strings = {" "})
  void failureBookSaveWithEmptyName(String bookTitle) {
    Author author = libraryFixtureSteps.insertAuthor("Джошуа", "Блох");

    BookSaveRequest request = new BookSaveRequest()
      .setBookTitle(bookTitle)
      .setAuthor(new AuthorForBook(author.getId()));

    bookSaveSteps.postBookSave(request, 500);
  }
}