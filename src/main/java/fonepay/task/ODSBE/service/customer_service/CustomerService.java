package fonepay.task.ODSBE.service.customer_service;

import fonepay.task.ODSBE.model.Customer;
import fonepay.task.ODSBE.service.CrudService;
import org.springframework.stereotype.Service;


@Service
public interface CustomerService extends CrudService<Customer> {

    Customer findCustomerByEmail(String email);

}
