package model.responseModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Author;
import entity.Customer;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAuthorAndUsersBooksResponse implements Serializable {

  private Long id;
  private String bookTitle;
  private Author author;
  private Customer customer;
}
