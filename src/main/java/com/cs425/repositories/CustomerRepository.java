package com.cs425.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cs425.model.Customer;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findById(UUID customerId);

    List<Customer> findByLastName(String lastName);

    Customer save(Customer customer);

    void deleteById(UUID customerId);

    boolean existsById(UUID customerId);
}