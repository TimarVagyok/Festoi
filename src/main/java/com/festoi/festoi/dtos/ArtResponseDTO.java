package com.festoi.festoi.dtos;

public class ArtResponseDTO {
    private long id;
    private String artName;
    private String artType;
    private Double price;
    private String artistName;
    private String URL;
    private Integer likesNumber;

    public ArtResponseDTO() {
    }

    public ArtResponseDTO(long id,
                          String artName,
                          String artType,
                          Double price,
                          String artistName,
                          String URL,
                          Integer likesNumber) {
        this.id = id;
        this.artName = artName;
        this.artType = artType;
        this.price = price;
        this.artistName = artistName;
        this.URL = URL;
        this.likesNumber = likesNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Integer getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }

    @Override
    public String toString() {
        return "ArtResponseDTO{" +
                "id=" + id +
                ", artName='" + artName + '\'' +
                ", artType='" + artType + '\'' +
                ", price=" + price +
                ", artistName='" + artistName + '\'' +
                ", URL='" + URL + '\'' +
                ", likesNumber=" + likesNumber +
                '}';
    }
}
