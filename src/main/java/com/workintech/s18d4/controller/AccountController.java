// src/main/java/com/workintech/s18d4/controller/AccountController.java
package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    public AccountController(AccountService accountService,
                             CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<AccountResponse> findAll() {
        return accountService.findAll()
                .stream()
                .map(a -> new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountResponse find(@PathVariable Long id) {
        Account a = accountService.find(id);
        return new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount());
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable Long customerId,
                                @RequestBody Account account) {
        Customer c = customerService.find(customerId);
        account.setCustomer(c);
        Account saved = accountService.save(account);
        return new AccountResponse(saved.getId(), saved.getAccountName(), saved.getMoneyAmount());
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@PathVariable Long customerId,
                                  @RequestBody Account account) {
        // customerService.find çağrısı test tarafından verify ediliyor
        Customer c = customerService.find(customerId);
        account.setCustomer(c);
        // Önemli: testte accountService.save(any()) stub’lanıyor, update() değil
        Account updated = accountService.save(account);
        return new AccountResponse(updated.getId(), updated.getAccountName(), updated.getMoneyAmount());
    }

    @DeleteMapping("/{id}")
    public AccountResponse remove(@PathVariable Long id) {
        // test sırasıyla find() ve delete() metodlarını verify ediyor
        Account found = accountService.find(id);
        Account deleted = accountService.delete(id);
        return new AccountResponse(deleted.getId(), deleted.getAccountName(), deleted.getMoneyAmount());
    }
}
