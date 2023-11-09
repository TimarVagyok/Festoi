package com.festoi.festoi.controllers;

import com.festoi.festoi.dtos.ArtDTO;
import com.festoi.festoi.dtos.ArtResponseDTO;
import com.festoi.festoi.models.Art;
import com.festoi.festoi.services.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/festoi/art")
public class ArtController {
    @Autowired
    private final ArtService artService;

    public ArtController(final ArtService artService) {
        this.artService = artService;
    }


    @GetMapping
    public ResponseEntity<List<ArtResponseDTO>> getAllArts() {
        List<ArtResponseDTO> listofArt =  artService.getAllPaintings();
        listofArt.stream().forEach(System.out::println);
        if (listofArt.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listofArt);
        }
        return ResponseEntity.ok(listofArt);
    }

    @PostMapping("/add-art")
    public ResponseEntity<Art> addNewArt(@RequestParam("artType") String artType,
                                         @RequestParam("artName") String artName,
                                         @RequestParam("price") Double price,
                                         @RequestPart("imageFile") MultipartFile imageFile) {
        ArtDTO artDTO = new ArtDTO(artType,artName,price);
        System.out.println("ArtDTO: " + artDTO);
        System.out.println("ImageFile: " + imageFile);

        if (artService.checkForArt(artDTO)) {
            Art art = artService.saveNewArt(artDTO,imageFile);
            return ResponseEntity.status(HttpStatus.OK).body(art);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/update-likes/{artName}")
    public ResponseEntity<ArtResponseDTO> updateLike(@PathVariable String artName,@RequestParam boolean isLiked) {
        Optional<Art> optionalArt = artService.getArtByArtName(artName);
        if (optionalArt.isPresent()) {
           return ResponseEntity.ok(artService.updateLikes(optionalArt.get(),isLiked));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
