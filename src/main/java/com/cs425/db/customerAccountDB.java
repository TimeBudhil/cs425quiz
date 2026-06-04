package com.cs425.db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cs425.model.Account;
import com.cs425.model.AccountType;
import com.cs425.model.Customer;

public class customerAccountDB {
    private static customerAccountDB instance;
    private List<Customer> customers; 

    private customerAccountDB(){
        this.customers = new ArrayList<>();
        initializeDb();
    }

    //singleton pattern.
    public static customerAccountDB getDBInstance(){
        if(instance == null){
            instance = new customerAccountDB();
        }
        return instance;
    }

    public List<Customer> getCustomers(){
        return this.customers;
    }

    public void initializeDb() {

        List<Customer> customers = new ArrayList<>();

        // Customers
        Customer bob = new Customer(
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                "Bob",
                "Jones");

        Customer anna = new Customer(
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                "Anna",
                "Smith");

        Customer carlos = new Customer(
                UUID.fromString("33333333-3333-3333-3333-333333333333"),
                "Carlos",
                "Jimenez");

        // Accounts

        // Account 1 -> Bob
        Account a1 = new Account(
                bob,
                1L,
                "AC1002",
                AccountType.CHECKINGS,
                LocalDate.parse("2016-05-17"),
                155900.50);

        // Account 2 -> Bob
        Account a2 = new Account(
                bob,
                2L,
                "AS1001",
                AccountType.SAVINGS,
                LocalDate.parse("2021-06-02"),
                12500.95);

        // Account 3 -> Carlos
        Account a3 = new Account(
                carlos,
                3L,
                "AS1003",
                AccountType.SAVINGS,
                LocalDate.parse("2016-07-11"),
                75000.00);

        // Account 4 -> Anna
        Account a4 = new Account(
                anna,
                4L,
                "AC1004",
                AccountType.CHECKINGS,
                LocalDate.parse("2024-03-29"),
                11700.99);

        // Link accounts to customers
        bob.addAccount(a1);
        bob.addAccount(a2);

        carlos.addAccount(a3);

        anna.addAccount(a4);

        // Add customers to database
        customers.add(bob);
        customers.add(anna);
        customers.add(carlos);

        this.customers = customers;
    }
}
