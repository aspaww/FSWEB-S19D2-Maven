// com/workintech/s18d4/service/AccountServiceImpl.java
package com.workintech.s18d4.service;

import com.workintech.s18d4.dao.AccountRepository;
import com.workintech.s18d4.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repo;

    public AccountServiceImpl(AccountRepository repo) {
        this.repo = repo;
    }

    public List<Account> findAll() {
        return repo.findAll();
    }

    public Account find(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Account save(Account account) {
        return repo.save(account);
    }

    public Account update(Long id, Account account) {
        account.setId(id);
        return repo.save(account);
    }

    public Account delete(Long id) {
        Optional<Account> opt = repo.findById(id);
        opt.ifPresent(repo::delete);
        return opt.orElse(null);
    }
}
