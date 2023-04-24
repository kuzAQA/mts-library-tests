package com.library;

import entity.Customer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import model.requestModel.CustomerSaveRequest;
import model.responseModel.CustomerSaveResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import steps.CustomerSaveSteps;

@Link("/customers/save")
@Feature("Добавление читателя")
@Story("POST /customers/save. Добавление нового читателя.")
public class CustomerSaveTests extends TestBase {

  private final CustomerSaveSteps customerSaveSteps = new CustomerSaveSteps();

  @Test
  @DisplayName("Позитивный тест /customers/save")
  @Description("Проверка успешного доавления автора, с корректными данными.")
  public void successAuthorSave() {

    CustomerSaveRequest request = new CustomerSaveRequest()
      .setFirstName("Мимо").setSecondName("Крокодил");

    CustomerSaveResponse response = customerSaveSteps.postCustomerSave(request, 200);
    customerSaveSteps.customerSaveResponseShouldBeCorrect(response);

    Customer savedAuthor = libraryFixtureSteps.findCustomerById(response.getCustomerId());
    customerSaveSteps.customerDataShouldBeAsInRequest(savedAuthor, request);
  }


  @Disabled("Требуется доработка API. По всем негатвным кейсам возвращается - 200.")
  @DisplayName("Негативный тест /authors/save")
  @Description("Проверка на получение ошибки при попытке сохранить автора с некорректными данными.")
  @ParameterizedTest(name = "{displayName} [{index}] Параметры: firstName=[{0}], secondName=[{1}]")
  @CsvSource(value = {
    "null,Мимо", "Крокодил,null",
    " ,Мимо", "Крокодил,",
    "123, Мимо", "Крокодил,921",
    "!^@*$,Мимо", "Крокодил,!^@*$"
  }, nullValues = {"null"})
  public void failureAuthorSaveWithInvalidData(String firstName, String secondName) {
    CustomerSaveRequest request = new CustomerSaveRequest()
      .setFirstName(firstName).setSecondName(secondName);

    customerSaveSteps.postCustomerSave(request, 500);
  }
}
