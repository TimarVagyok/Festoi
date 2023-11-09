package com.festoi.festoi.dtos;

import org.springframework.web.multipart.MultipartFile;

public class ArtDTO {
    private String artType;
    private String artName;
    private Double price;

    public ArtDTO(String artType, String artName, Double price) {
        this.artType = artType;
        this.artName = artName;
        this.price = price;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
