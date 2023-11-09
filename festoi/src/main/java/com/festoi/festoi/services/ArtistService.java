package com.festoi.festoi.services;

import com.festoi.festoi.dtos.UserDTO;
import com.festoi.festoi.models.Artist;
import com.festoi.festoi.models.Role;
import com.festoi.festoi.repositories.ArtistRepo;
import com.festoi.festoi.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//TODO : ADDING ROLES
@Service
public class ArtistService {
    private final RoleRepo roleRepo;
    private final ArtistRepo artistRepo;
   @Autowired
   private final PasswordEncoder passwordEncoder;

    public ArtistService(RoleRepo roleRepo, ArtistRepo artistRepo, PasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.artistRepo = artistRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Artist registerArtist(UserDTO userDTO) {
        Optional<Artist> checkArtist = artistRepo.findByUsername(userDTO.getUsername());
        if (!checkArtist.isPresent()) {
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            Role artistRole = roleRepo.findByAuthority("ARTIST").get();
            Set<Role> authorities = Set.of(artistRole);
            return artistRepo.save(new Artist(userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getUsername(),
                    encodedPassword,
                    userDTO.getEmail(),
                    authorities));
        }
        return null;
    }
    /*
    * This method is using the authentication to check the user logged in if it is an artist or not
    * if yes it returns true
    * if no it returns false*/
    public boolean isLoggedInArtist(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(artistRepo.findByUsername(userDetails.getUsername()).isPresent());
        return artistRepo.findByUsername(userDetails.getUsername()).isPresent();
    }


}
