package com.festoi.festoi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "ARTS")

public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ART_ID")
    @JsonProperty("art_id")
    private Integer id;
    @Column(name = "ART_NAME")
    @JsonProperty("art_name")
    private String artName;
    @Column(name = "ART_TYPE")
    @JsonProperty("art_type")
    private String artType;
    @Column(name = "PRICE")
    @JsonProperty("price")
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ARTIST_ID")
    @JsonProperty("artist")
    private Artist artist;
    @Column(name = "URL")
    private String URL;

    @Column(name = "LIKES_NUMBER")
    private Integer likesNumber = 0;

    public Art() {
    }

    public Art(String artName, String artType, Double price, Artist artist, String URL) {
        this.artName = artName;
        this.artType = artType;
        this.price = price;
        this.artist = artist;
        this.URL = URL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
        return "Art{" +
                "id=" + id +
                ", artName='" + artName + '\'' +
                ", artType='" + artType + '\'' +
                ", price=" + price +
                ", artist=" + artist +
                ", URL='" + URL + '\'' +
                ", likesNumber=" + likesNumber +
                '}';
    }
}
