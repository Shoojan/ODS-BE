package fonepay.task.ODSBE.security.service;

import fonepay.task.ODSBE.security.model.UserSecurity;
import fonepay.task.ODSBE.service.customer_service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return new UserSecurity(customerService.findCustomerByEmail(email));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with Email : " + email);
        }
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(long id) {
        try {
            return new UserSecurity(customerService.findDataById(id));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with id : " + id);
        }
    }
}
