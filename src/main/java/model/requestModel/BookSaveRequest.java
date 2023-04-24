package model.requestModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Author;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookSaveRequest implements Serializable {

  private String bookTitle;

  private AuthorForBook author;

  private CustomerForBook customer;

}
