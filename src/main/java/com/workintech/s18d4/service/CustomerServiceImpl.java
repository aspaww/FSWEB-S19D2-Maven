// com/workintech/s18d4/service/CustomerServiceImpl.java
package com.workintech.s18d4.service;

import com.workintech.s18d4.dao.CustomerRepository;
import com.workintech.s18d4.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> findAll() {
        return repo.findAll();
    }

    public Customer find(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Customer save(Customer customer) {
        return repo.save(customer);
    }

    public Customer update(Long id, Customer customer) {
        customer.setId(id);
        return repo.save(customer);
    }

    public Customer delete(Long id) {
        Optional<Customer> opt = repo.findById(id);
        opt.ifPresent(repo::delete);
        return opt.orElse(null);
    }
}
