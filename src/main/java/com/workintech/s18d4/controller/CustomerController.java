// src/main/java/com/workintech/s18d4/controller/CustomerController.java
package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse saveCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.save(customer);
        return new CustomerResponse(saved.getId(), saved.getEmail(), saved.getSalary());
    }

    @GetMapping("/{id}")
    public CustomerResponse findCustomer(@PathVariable Long id) {
        Customer c = customerService.find(id);
        return new CustomerResponse(c.getId(), c.getEmail(), c.getSalary());
    }

    @GetMapping
    public java.util.List<CustomerResponse> findAllCustomers() {
        return customerService.findAll().stream()
                .map(c -> new CustomerResponse(c.getId(), c.getEmail(), c.getSalary()))
                .toList();
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id,
                                           @RequestBody Customer customer) {
        Customer updated = customerService.update(id, customer);
        return new CustomerResponse(updated.getId(), updated.getEmail(), updated.getSalary());
    }

    @DeleteMapping("/{id}")
    public CustomerResponse deleteCustomer(@PathVariable Long id) {
        Customer deleted = customerService.delete(id);
        return new CustomerResponse(deleted.getId(), deleted.getEmail(), deleted.getSalary());
    }
}
