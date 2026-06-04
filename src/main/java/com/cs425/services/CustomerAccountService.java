package com.cs425.services;

import java.util.List;

import com.cs425.model.Account;
import com.cs425.model.AccountTier;

import java.util.Map;
import java.util.stream.Collectors;

import com.cs425.model.AccountType;
import com.cs425.model.Customer;
import com.cs425.repositories.AccountDAO;
import com.cs425.repositories.CustomerDAO;

public class CustomerAccountService {

    private static CustomerAccountService instance;

    private final AccountDAO accountDAO;
    private final CustomerDAO customerDAO;

    private CustomerAccountService() {
        accountDAO = AccountDAO.getAccountRepositoryInstance();
        customerDAO = CustomerDAO.getCustomerRepositoryInstance();
    }

    public static CustomerAccountService getInstance() {
        if (instance == null) {
            instance = new CustomerAccountService();
        }
        return instance;
    }

    public List<Account> getRegularAccounts() {
        return accountDAO.findAll()
                .stream()
                .filter(a -> a.determineAccountTier() == AccountTier.REGULAR)
                .collect(Collectors.toList());
    }

    public List<Account> getSilverAccounts() {
        return accountDAO.findAll()
                .stream()
                .filter(a -> a.determineAccountTier() == AccountTier.SILVER)
                .collect(Collectors.toList());
    }

    public List<Account> getGoldAccounts() {
        return accountDAO.findAll()
                .stream()
                .filter(a -> a.determineAccountTier() == AccountTier.GOLD)
                .collect(Collectors.toList());
    }

    public List<Account> getPlatinumAccounts() {
        return accountDAO.findAll()
                .stream()
                .filter(a -> a.determineAccountTier() == AccountTier.PLATINUM)
                .collect(Collectors.toList());
    }

    public void printAccountsByCustomer() {

        for (Customer customer : customerDAO.findAll()) {

            System.out.println(
                    customer.getFirstName() + " "
                    + customer.getLastName());

            customer.getAccounts()
                    .forEach(account ->
                        System.out.println("\t" + account));
        }
    }

    public List<Account> getAllAccountsSortedByBalanceDesc() {

        return accountDAO.findAll()
                .stream()
                .sorted((a1, a2) ->
                        Double.compare(a2.getBalance(),
                                    a1.getBalance()))
                .toList();
    }

    public void printAccountsAsJSON(List<Account> accounts) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < accounts.size(); i++) {
            json.append("  ")
                .append(accounts.get(i).toJSON());

            if (i < accounts.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        System.out.println(json.toString());
    }

    public double getLiquidityPosition() {
        return accountDAO.findAll()
                .stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    public void printPlatinumAccountsAsJSON() {
        printAccountsAsJSON(getPlatinumAccounts());
    }
}


