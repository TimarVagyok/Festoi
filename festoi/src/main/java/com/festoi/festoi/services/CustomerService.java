package com.festoi.festoi.services;

import com.festoi.festoi.dtos.UserDTO;
import com.festoi.festoi.models.Artist;
import com.festoi.festoi.models.Customer;
import com.festoi.festoi.models.Role;
import com.festoi.festoi.repositories.CustomerRepo;
import com.festoi.festoi.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CustomerService {


    private final CustomerRepo customerRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepo customerRepo,
                           RoleRepo roleRepo,
                           PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer registerCustomer(UserDTO userDTO) {
        Optional<Customer> checkArtist = customerRepo.findByUsername(userDTO.getUsername());
        if (!checkArtist.isPresent()) {
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            Role userRole = roleRepo.findByAuthority("CUSTOMER").get();
            Set<Role> authorities = Set.of(userRole);
            return customerRepo.save(new Customer(userDTO.getUsername(),
                    encodedPassword,
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getEmail(),
                    authorities));
        }
        return null;
    }

}
