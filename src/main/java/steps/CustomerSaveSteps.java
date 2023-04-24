package steps;

import entity.Customer;
import io.qameta.allure.Step;
import model.requestModel.CustomerSaveRequest;
import model.responseModel.CustomerSaveResponse;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.ErrorsMessage.errorMessage;
import static utils.SpecsForTests.librarySpec;

public class CustomerSaveSteps {

  @Step("Отправляем POST /customer/save запрос. Создание читателя")
  public CustomerSaveResponse postCustomerSave(CustomerSaveRequest body, int expectedStatusCode) {
    return given()
      .spec(librarySpec)
      .body(body)
      .when()
      .post("users/save")
      .then()
      .statusCode(expectedStatusCode)
      .extract().as(CustomerSaveResponse.class);
  }

  @Step("Проверка ответа запроса на сохранение читателя")
  public void customerSaveResponseShouldBeCorrect(CustomerSaveResponse response) {
    assertTrue(response.getCustomerId() > 0, "customerId должно быть больше 0.");
  }

  @Step("Проверка данных сохранённого читателя")
  public void customerDataShouldBeAsInRequest(Customer customer, CustomerSaveRequest request) {
    assertEquals(request.getFirstName(), customer.getFirstName(), errorMessage("firstMame"));
    assertEquals(request.getSecondName(), customer.getSecondName(), errorMessage("secondName"));
  }
}
