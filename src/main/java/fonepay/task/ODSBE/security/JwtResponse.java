package fonepay.task.ODSBE.security;

import java.io.Serializable;

public record JwtResponse(String jwtToken) implements Serializable {
}
