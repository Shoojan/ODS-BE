package fonepay.task.ODSBE.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor //need default constructor for JSON Parsing
public class JwtRequest implements Serializable {

    private String email;
    private String password;

}
