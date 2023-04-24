package config;

import entity.Author;
import entity.Book;
import entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class LibraryDatabaseConfiguration {

  private static SessionFactory sessionFactory;

  private LibraryDatabaseConfiguration() {
  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration().configure()
          .addAnnotatedClass(Book.class)
          .addAnnotatedClass(Author.class)
          .addAnnotatedClass(Customer.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(
          configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());

      } catch (Exception e) {
        System.out.println("Исключение!" + e);
      }
    }
    return sessionFactory;
  }
}
