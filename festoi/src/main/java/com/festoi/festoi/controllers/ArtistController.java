package com.festoi.festoi.controllers;

import com.festoi.festoi.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("festoi/artists")
public class ArtistController {
    @Autowired
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/check")
    public boolean checkForArtistLoggedIn(){
        System.out.println("checkForArtist controller got triggerd +++ ");
        return artistService.isLoggedInArtist();
    }
}
