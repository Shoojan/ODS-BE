package fonepay.task.ODSBE.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDetail {
    long customerId;
    String fullName;
    String email;
    String mobile;
    String address;
}
