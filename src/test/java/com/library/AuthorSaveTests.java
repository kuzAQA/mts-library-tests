package com.library;

import entity.Author;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import model.requestModel.AuthorSaveRequest;
import model.responseModel.AuthorSaveResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import steps.AuthorSaveSteps;

@Link("/authors/save")
@Feature("Добавление автора")
@Story("POST /authors/save. Добавление нового автора.")
public class AuthorSaveTests extends TestBase{

  private final AuthorSaveSteps authorSaveSteps = new AuthorSaveSteps();

  @Test
  @DisplayName("Позитивный тест /authors/save")
  @Description("Проверка успешного доавления автора, с корректными данными.")
  public void successAuthorSave() {
    AuthorSaveRequest request = new AuthorSaveRequest()
      .setFirstName("Николай").setSecondName("Гоголь");

    AuthorSaveResponse response = authorSaveSteps.postAuthorSave(request, 200);
    authorSaveSteps.authorSaveResponseShouldBeCorrect(response);

    Author savedAuthor = libraryFixtureSteps.findAuthorById(response.getAuthorId());
    authorSaveSteps.authorDataShouldBeAsInRequest(savedAuthor, request);
  }


  @Disabled("Требуется доработка API. По всем негатвным кейсам возвращается - 200.")
  @DisplayName("Негативный тест /authors/save")
  @Description("Проверка на получение ошибки при попытке сохранить автора с некорректными данными.")
  @ParameterizedTest(name = "{displayName} [{index}] Параметры: firstName=[{0}], secondName=[{1}]")
  @CsvSource(value = {
    "null,Гоголь", "Николай,null",
    " ,Гоголь", "Николай,",
    "123, Гоголь", "Николай,921",
    "!^@*$,Гоголь", "Николай,!^@*$"})
  public void failureAuthorSaveWithInvalidData(String firstName, String secondName) {
    AuthorSaveRequest request = new AuthorSaveRequest()
      .setFirstName(firstName).setSecondName(secondName);

    authorSaveSteps.postAuthorSave(request, 500);
  }
}
