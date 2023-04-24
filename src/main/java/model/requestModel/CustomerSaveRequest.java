package model.requestModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerSaveRequest implements Serializable {

  private Long id;

  private String firstName;

  private String secondName;

}
