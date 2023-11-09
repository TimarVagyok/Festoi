package com.festoi.festoi.mappers;

import com.festoi.festoi.dtos.ArtResponseDTO;
import com.festoi.festoi.models.Art;
import org.springframework.stereotype.Component;

@Component
public class ArtMapper {
    public ArtResponseDTO toResponse(Art art){
        ArtResponseDTO artResponseDTO = new ArtResponseDTO();
        artResponseDTO.setId(art.getId());
        artResponseDTO.setArtName(art.getArtName());
        artResponseDTO.setArtType(art.getArtType());
        artResponseDTO.setPrice(art.getPrice());
        artResponseDTO.setArtistName(art.getArtist().getFirstName() + " " + art.getArtist().getLastName());
        artResponseDTO.setURL(art.getURL());
        artResponseDTO.setLikesNumber(art.getLikesNumber());
        return artResponseDTO;
    }
}
