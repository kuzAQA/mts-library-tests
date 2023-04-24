package repository;

import config.LibraryDatabaseConfiguration;
import entity.Author;
import entity.Book;
import entity.Customer;
import io.qameta.allure.Step;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LibraryFixtureSteps {

  Session session;

  public LibraryFixtureSteps() {
    session = LibraryDatabaseConfiguration.getSessionFactory().openSession();
  }

  public void closeSession() {
    if (session != null && session.isOpen()) {
      session.close();
    }
  }

  @Step("Создать автора с именем {0} {1}")
  public Author insertAuthor(String firstName, String secondName) {
    Author author = new Author().setFirstName(firstName).setSecondName(secondName);

    Transaction tr = session.beginTransaction();
    session.persist(author);
    tr.commit();
    return author;
  }

  @Step("Создать читателя с именем {0} {1}")
  public Customer insertCustomer(String firstName, String secondName) {
    Customer customer = new Customer().setFirstName(firstName).setSecondName(secondName);

    Transaction tr = session.beginTransaction();
    session.persist(customer);
    tr.commit();
    return customer;
  }

  @Step("Создать книгу с названием {1}")
  public Book insertBook(String bookTitle, Long authorId) {
    Book book = new Book()
      .setBookTitle(bookTitle)
      .setAuthorId(authorId);

    Transaction tr = session.beginTransaction();
    session.persist(book);
    tr.commit();
    return book;
  }

  @Step("Найти автора c id = {id}")
  public Author findAuthorById(Long authorId) {
    return session.find(Author.class, authorId);
  }

  @Step("Найти читателя c id = {id}")
  public Customer findCustomerById(Long customerId) {
    return session.find(Customer.class, customerId);
  }

  @Step("Найти книгу c id = {id}")
  public Book findBookById(Long bookId) {
    return session.find(Book.class, bookId);
  }
}
