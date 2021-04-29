package fonepay.task.ODSBE.service;

import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.model.Customer;
import fonepay.task.ODSBE.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public List<Customer> findAllCustomers() {
        return customerRepository.findAllByDeletedAt(null);
    }

    public Customer findCustomerById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Customer of id " + id + " was not found!"));
        if (customer.getDeletedAt() == null)
            return customer;
        else throw new ApiRequestException("Customer not valid!");
    }

    public Customer addCustomer(Customer customer) {
        customer.setCreatedAt(LocalDate.now());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        customer.setUpdatedAt(LocalDate.now());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        Customer customer = findCustomerById(id);
        customer.setDeletedAt(LocalDate.now());
        customerRepository.save(customer);
    }
}
