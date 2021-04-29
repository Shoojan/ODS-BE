package fonepay.task.ODSBE.controller;

import fonepay.task.ODSBE.model.Customer;
import fonepay.task.ODSBE.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController implements CrudController<Customer> {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllData() {
        List<Customer> customers = customerService.findAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getDataById(@PathVariable("id") long id) {
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addData(@RequestBody Customer customer) {
        Customer newcustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(newcustomer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> updateData(@RequestBody Customer customer) {
        Customer updatedcustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(updatedcustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteDataById(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
