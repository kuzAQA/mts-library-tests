package model.responseModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Book;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ListBook<B> implements Serializable {

  private List<Book> books;

}