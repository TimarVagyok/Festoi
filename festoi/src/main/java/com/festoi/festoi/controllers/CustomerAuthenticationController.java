package com.festoi.festoi.controllers;

import com.festoi.festoi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class CustomerAuthenticationController {

    @Autowired
    private final CustomerService customerService;

    public CustomerAuthenticationController(final CustomerService customerService) {
        this.customerService = customerService;
    }

}
