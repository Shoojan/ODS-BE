package fonepay.task.ODSBE.controller;

import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.security.JwtAuthenticationManager;
import fonepay.task.ODSBE.security.model.JwtRequest;
import fonepay.task.ODSBE.security.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class LoginController {

    private final JwtAuthenticationManager authenticationManager;

    @Autowired
    public LoginController(JwtAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("status")
    public ResponseEntity<String> serverStatus() {
        return new ResponseEntity<>("Server is running...", HttpStatus.OK);
    }

    @PostMapping(value = "authenticate")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) {

        try {
//            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = authenticationManager.createAuthenticationToken(authenticationRequest);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (UsernameNotFoundException e) {
            throw new ApiRequestException("Username not found [" + e.getMessage() + "]", e);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }

    }
}
