package fonepay.task.ODSBE.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class JwtAuthenticationManager {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
//    private final CustomerUserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationManager(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
//            final CustomerUserDetailsService userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return jwtTokenProvider.generateToken(authenticate(authenticationRequest));
    }

    private Authentication authenticate(JwtRequest authenticationRequest) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
