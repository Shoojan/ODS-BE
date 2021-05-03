package fonepay.task.ODSBE.security;

import fonepay.task.ODSBE.security.model.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationManager {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtAuthenticationManager(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
//        final Customer customer = customerService.findCustomerByEmail(authenticationRequest.getEmail());
        return jwtTokenProvider.generateToken(authenticate(authenticationRequest));
    }

    private Authentication authenticate(JwtRequest authenticationRequest) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
