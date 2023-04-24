package model.requestModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorSaveRequest implements Serializable {

  private Long id;

  private String firstName;

  private String secondName;

}
