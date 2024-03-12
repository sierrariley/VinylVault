package com.example.vinylvault.Pojo;

import java.util.ArrayList;

public class Album {
    private int id;
    private String name;
    private String artistName;
    private String genre;
    private String artwork;


    public Album(int id, String name, String artistName, String genre, String artwork) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.genre = genre;
        this.artwork = artwork;


    }

    public Album() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }
}
