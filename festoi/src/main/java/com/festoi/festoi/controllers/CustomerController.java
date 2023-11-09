package com.festoi.festoi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")

public class CustomerController {

    @GetMapping
    public String getCustomers() {
        return "You have no customer yet";
    }
}
