package com.festoi.festoi.repositories;

import com.festoi.festoi.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends CrudRepository<Customer,Long> {

    Optional<Customer> findByUsername(String username);
}
