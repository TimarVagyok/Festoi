package com.festoi.festoi.config;

import com.festoi.festoi.models.Art;
import com.festoi.festoi.models.Artist;
import com.festoi.festoi.models.Customer;
import com.festoi.festoi.models.Role;
import com.festoi.festoi.repositories.ArtRepo;
import com.festoi.festoi.repositories.ArtistRepo;
import com.festoi.festoi.repositories.CustomerRepo;
import com.festoi.festoi.repositories.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {
    private final RoleRepo roleRepo;
    private final CustomerRepo customerRepo;
    private final ArtistRepo artistRepo;
    private final ArtRepo artRepo;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(RoleRepo roleRepo, CustomerRepo customerRepo, ArtistRepo artistRepo,ArtRepo artRepo, PasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.customerRepo = customerRepo;
        this.artistRepo = artistRepo;
        this.artRepo = artRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (roleRepo.findByAuthority("ADMIN").isEmpty()) {
            roleRepo.save(new Role("ADMIN"));
        }
        if (roleRepo.findByAuthority("ARTIST").isEmpty()) {
            roleRepo.save(new Role("ARTIST"));
        }
        if (roleRepo.findByAuthority("CUSTOMER").isEmpty()) {
            roleRepo.save(new Role("CUSTOMER"));
        }


        if (customerRepo.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepo.findByAuthority("ADMIN").get();
            Customer admin = new Customer("admin",
                    passwordEncoder.encode("password"),
                    "Admin",
                    "User",
                    "admin@example.com");
            admin.setAuthorities(Set.of(adminRole));
            customerRepo.save(admin);
        }

        if (artistRepo.findByUsername("artist").isEmpty()) {
            Role artistRole = roleRepo.findByAuthority("ARTIST").get();
            Artist artist = new Artist("Emese",
                    "Gyokeres",
                    "mesi",
                    passwordEncoder.encode("password"),
                    "mesi@example.com");
            artist.setAuthorities(Set.of(artistRole));
            artistRepo.save(artist);

            //Adding art to test if art is working properly
            artRepo.save(new Art("Starry Night", "Van Gogh" , 5000.0 ,artist,"/photos/starry.jpeg"));
            artRepo.save(new Art("Budapest", "Meow meow" , 5000.0 ,artist,"/photos/budapest.jpeg"));
        }



        // You can add more customers and artists if needed
    }
}
