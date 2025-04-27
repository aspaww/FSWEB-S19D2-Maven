// com/workintech/s18d4/controller/AddressController.java
package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/address")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public List<Address> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable Long id) {
        return service.find(id);
    }

    @PostMapping
    public Address create(@RequestBody Address addr) {
        return service.save(addr);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @RequestBody Address addr) {
        return service.update(id, addr);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
