package com.cs425.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cs425.model.Account;

public interface AccountRepository {

    List<Account> findAll();

    Optional<Account> findById(long accountId);

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(UUID customerId);

    Account save(Account account);

    void deleteById(long accountId);

    boolean existsById(long accountId);
}