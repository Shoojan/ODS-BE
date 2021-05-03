package fonepay.task.ODSBE.controller;

import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.model.Customer;
import fonepay.task.ODSBE.security.JwtAuthenticationManager;
import fonepay.task.ODSBE.security.model.JwtRequest;
import fonepay.task.ODSBE.security.model.JwtResponse;
import fonepay.task.ODSBE.security.model.ProfileDetail;
import fonepay.task.ODSBE.service.customer_service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class LoginController {

    private final JwtAuthenticationManager authenticationManager;
    private final CustomerService customerService;

    @Autowired
    public LoginController(JwtAuthenticationManager authenticationManager, CustomerService customerService) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
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
            return ResponseEntity.ok(getJwtResponse(token, authenticationRequest.getEmail()));
        } catch (UsernameNotFoundException e) {
            throw new ApiRequestException("Username not found [" + e.getMessage() + "]", e);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }

    }

    private Object getJwtResponse(String token, String email) {
        Customer customer = customerService.findCustomerByEmail(email);
        return new JwtResponse(token,
                new ProfileDetail(
                        customer.getId(),
                        customer.getFirstName() + " " + customer.getLastName(),
                        customer.getEmail(),
                        customer.getMobile(),
                        customer.getAddress()
                )
        );
    }
}
