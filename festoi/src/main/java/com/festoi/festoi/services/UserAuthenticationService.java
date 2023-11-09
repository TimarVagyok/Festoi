package com.festoi.festoi.services;

import com.festoi.festoi.models.Artist;
import com.festoi.festoi.models.Customer;
import com.festoi.festoi.repositories.ArtistRepo;
import com.festoi.festoi.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService implements UserDetailsService {
    @Autowired
    final PasswordEncoder encoder;
    final  CustomerRepo customerRepo;

    final ArtistRepo artistRepo;

    public UserAuthenticationService(final PasswordEncoder encoder,
                                     final CustomerRepo customerRepo,
                                     final ArtistRepo artistRepo) {
        this.encoder = encoder;
        this.customerRepo = customerRepo;
        this.artistRepo = artistRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");
        Optional<Customer> possibleCustomer = customerRepo.findByUsername(username);
        if(possibleCustomer.isEmpty()) {
            Optional<Artist> possibleArtist = artistRepo.findByUsername(username);
            if (possibleArtist.isEmpty()){
                throw new UsernameNotFoundException("No such username");
            }
            return possibleArtist.get();
        }
        return possibleCustomer.get();
    }
}
