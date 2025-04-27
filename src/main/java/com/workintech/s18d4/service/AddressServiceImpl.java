// com/workintech/s18d4/service/AddressServiceImpl.java
package com.workintech.s18d4.service;

import com.workintech.s18d4.dao.AddressRepository;
import com.workintech.s18d4.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repo;

    public AddressServiceImpl(AddressRepository repo) {
        this.repo = repo;
    }

    public List<Address> findAll() {
        return repo.findAll();
    }

    public Address find(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Address save(Address address) {
        return repo.save(address);
    }

    public Address update(Long id, Address address) {
        address.setId(id);
        return repo.save(address);
    }

    public void delete(Long id) {
        repo.findById(id).ifPresent(repo::delete);
    }
}
