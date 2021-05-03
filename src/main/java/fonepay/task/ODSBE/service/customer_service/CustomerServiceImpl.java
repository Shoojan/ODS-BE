package fonepay.task.ODSBE.service.customer_service;

import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.model.Customer;
import fonepay.task.ODSBE.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public record CustomerServiceImpl(CustomerRepository customerRepository) implements CustomerService {

    public List<Customer> findAllData() {
        return customerRepository.findAllByDeletedAt(null);
    }

    public Customer findDataById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Customer of id " + id + " was not found!"));
        if (customer.getDeletedAt() == null)
            return customer;
        else throw new ApiRequestException("Customer not valid!");
    }

    public Customer findCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Customer of email `" + email + "` was not found!"));
        if (customer.getDeletedAt() == null)
            return customer;
        else throw new ApiRequestException("Customer not valid!");
    }

    public Customer addData(Customer customer) {
        customer.setCreatedAt(LocalDate.now());
        return customerRepository.save(customer);
    }

    public Customer updateData(Customer customer) {
        customer.setUpdatedAt(LocalDate.now());
        return customerRepository.save(customer);
    }

    public void deleteData(long id) {
        Customer customer = findDataById(id);
        customer.setDeletedAt(LocalDate.now());
        customerRepository.save(customer);
    }
}
