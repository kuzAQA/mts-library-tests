package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static utils.ErrorsMessage.errorMessage;

import entity.Author;
import entity.Book;
import entity.Customer;
import java.util.List;
import model.responseModel.GetAuthorAndUsersBooksResponse;

public class AssertsBookList {

  public static void booksListResponseShouldBeCorrect(
    List<GetAuthorAndUsersBooksResponse> listBookResponse,
    Book book, Author author, Customer customer) {
    for (GetAuthorAndUsersBooksResponse e : listBookResponse) {
      assertEquals(book.getBookTitle(), e.getBookTitle(),
        errorMessage("bookTitle"));
      assertEquals(author.getFirstName(), e.getAuthor().getFirstName(),
        errorMessage("author.firstName"));
      assertEquals(author.getSecondName(), e.getAuthor().getSecondName(),
        errorMessage("author.secondName"));

      if (e.getCustomer() == null) {
        assertNull(e.getCustomer());
      } else {
        assertEquals(customer.getFirstName(), e.getCustomer().getFirstName(),
          errorMessage("customer.firstName"));
        assertEquals(customer.getSecondName(), e.getCustomer().getSecondName(),
          errorMessage("customer.secondName"));
      }
    }
  }
}
