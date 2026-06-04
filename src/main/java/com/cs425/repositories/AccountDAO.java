package com.cs425.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.cs425.db.customerAccountDB;
import com.cs425.model.Account;
import com.cs425.model.Customer;

public class AccountDAO implements AccountRepository {
    private static AccountDAO instance;
    private final customerAccountDB db;

    private AccountDAO() {
        this.db = customerAccountDB.getDBInstance();
    }

    public static AccountDAO getAccountRepositoryInstance(){
        if(instance == null){
            instance = new AccountDAO();
        }
        return instance;
    }

    @Override
    public List<Account> findAll() {
        return db.getCustomers()
                 .stream()
                 .flatMap(customer -> customer.getAccounts().stream())
                 .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(long accountId) {
        return findAll()
                .stream()
                .filter(a -> a.getAccountId() == accountId)
                .findFirst();
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return findAll()
                .stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    @Override
    public List<Account> findByCustomerId(UUID customerId) {
        return db.getCustomers()
                 .stream()
                 .filter(c -> c.getCustomerId().equals(customerId))
                 .findFirst()
                 .map(Customer::getAccounts)
                 .orElse(List.of());
    }

    @Override
    public Account save(Account account) {

        Optional<Account> existing = findById(account.getAccountId());

        if (existing.isPresent()) {
            deleteById(account.getAccountId());
        }

        account.getCustomer().addAccount(account);

        return account;
    }

    @Override
    public void deleteById(long accountId) {

        db.getCustomers().forEach(customer ->
            customer.getAccounts()
                    .removeIf(account ->
                        account.getAccountId() == accountId)
        );
    }

    @Override
    public boolean existsById(long accountId) {
        return findById(accountId).isPresent();
    }
}