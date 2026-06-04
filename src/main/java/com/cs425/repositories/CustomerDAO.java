package com.cs425.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.cs425.db.customerAccountDB;
import com.cs425.model.Customer;

public class CustomerDAO implements CustomerRepository {
    private static CustomerDAO instance;
    private final customerAccountDB db;

    private CustomerDAO() {
        this.db = customerAccountDB.getDBInstance();
    }

    public static CustomerDAO getCustomerRepositoryInstance(){
        if(instance == null){
            instance = new CustomerDAO();
        }
        return instance;
    }

    @Override
    public List<Customer> findAll() {
        return db.getCustomers();
    }

    @Override
    public Optional<Customer> findById(UUID customerId) {
        return db.getCustomers()
                .stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst();
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return db.getCustomers()
                .stream()
                .filter(c -> c.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public Customer save(Customer customer) {

        Optional<Customer> existing = findById(customer.getCustomerId());

        if (existing.isPresent()) {
            db.getCustomers().remove(existing.get());
        }

        db.getCustomers().add(customer);
        return customer;
    }

    @Override
    public void deleteById(UUID customerId) {
        db.getCustomers()
          .removeIf(c -> c.getCustomerId().equals(customerId));
    }

    @Override
    public boolean existsById(UUID customerId) {
        return db.getCustomers()
                .stream()
                .anyMatch(c -> c.getCustomerId().equals(customerId));
    }
}