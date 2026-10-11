package com.festoi.festoi.services;

import com.festoi.festoi.dtos.ArtDTO;
import com.festoi.festoi.dtos.ArtResponseDTO;
import com.festoi.festoi.mappers.ArtMapper;
import com.festoi.festoi.models.Art;
import com.festoi.festoi.models.Artist;
import com.festoi.festoi.models.Role;
import com.festoi.festoi.repositories.ArtRepo;
import com.festoi.festoi.repositories.ArtistRepo;
import com.festoi.festoi.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ArtService {
    private final ArtRepo artRepo;
    private final ArtistRepo artistRepo;


    @Autowired
    private final FileStorageService fileStorageService;
    @Autowired
    private final ArtMapper artMapper;

    public ArtService(ArtRepo artRepo,
                      ArtistRepo artistRepo,
                      FileStorageService fileStorageService,
                      ArtMapper artMapper) {
        this.artRepo = artRepo;
        this.artistRepo = artistRepo;
        this.fileStorageService = fileStorageService;
        this.artMapper = artMapper;
    }

    public List<ArtResponseDTO> getAllPaintings() {
        List<Art> list =  artRepo.findAll();
        List<ArtResponseDTO> mappedList = new ArrayList<>();
        for (Art art:list) {
            mappedList.add(artMapper.toResponse(art));
        }
        return mappedList;
    }
    public Iterable<Art> getAllPaintingsByArtist(Artist artist) {
        return artRepo.getAllByArtist(artist);
    }

    public Optional<Art> getArtByArtName(String artName) {
        return artRepo.findArtByArtName(artName);
    }
    public ArtResponseDTO updateLikes(Art art,boolean isliked) {
        int newLikeCount = isliked ? art.getLikesNumber()+1 : art.getLikesNumber() -1;
        art.setLikesNumber(newLikeCount);
        return artMapper.toResponse(artRepo.save(art));
    }

    public Art saveNewArt(ArtDTO artDTO, MultipartFile imageFile) {
        //artist getting from auth
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Artist artist1 = artistRepo.findByUsername(userDetails.getUsername()).get();
        //the photo thing
        String fileName = imageFile.getOriginalFilename();
        try {
            fileStorageService.storeFile(imageFile, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fileUrl = fileStorageService.generateFileUrl(fileName);
        System.out.println(fileUrl+ " The url");


        Art art = new Art();
        art.setArtName(artDTO.getArtName());
        art.setArtType(artDTO.getArtType());
        art.setPrice(artDTO.getPrice());
        art.setURL(fileUrl);

        art.setArtist(artist1);
        //TODO : check for artist name and save it
        return artRepo.save(art);
    }

    public boolean checkForArt(ArtDTO artDTO) {
        return artRepo.findArtByArtName(artDTO.getArtName()).isEmpty();
    }
}
