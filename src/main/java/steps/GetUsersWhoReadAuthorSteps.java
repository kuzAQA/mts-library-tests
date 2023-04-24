package steps;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static utils.SpecsForTests.librarySpec;

import io.qameta.allure.Step;
import java.util.List;
import model.responseModel.GetAuthorAndUsersBooksResponse;

public class GetUsersWhoReadAuthorSteps {
  @Step("Получение пользователей, которые читают автора, id")
  public List<GetAuthorAndUsersBooksResponse> getUsersWhoReadAuthors(Long id) {
    return given()
      .spec(librarySpec)
      .when()
      .get(format("authors/%s/users", id))
      .then()
      .log().body()
      .extract().jsonPath().getList("", GetAuthorAndUsersBooksResponse.class);
  }
}
