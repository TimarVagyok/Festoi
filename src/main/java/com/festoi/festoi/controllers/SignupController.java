package com.festoi.festoi.controllers;

import com.festoi.festoi.dtos.UserDTO;
import com.festoi.festoi.models.Artist;
import com.festoi.festoi.models.Customer;
import com.festoi.festoi.models.FestoiUserDTO;
import com.festoi.festoi.services.ArtistService;
import com.festoi.festoi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/festoi/signup")
public class SignupController {
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final ArtistService artistService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public SignupController(CustomerService customerService, ArtistService artistService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.artistService = artistService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping("/new-user")
    public ResponseEntity<FestoiUserDTO> saveNewUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        // TODO : what if registration failed?
        if (userDTO.isArtist()) {
            Artist artist = artistService.registerArtist(userDTO);
            if (artist == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(artist);
        }
        Customer customer =  customerService.registerCustomer(userDTO);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
