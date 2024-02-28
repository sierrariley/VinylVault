package com.example.vinylvault.Pojo;

import java.util.ArrayList;

public class Album {
    private int id;
    private String name;
    private String artistName;
    private String genre;
    private ArrayList<Track> tracks;

    public Album(int id, String name, String artistName, String genre, ArrayList<Track> tracks) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.genre = genre;
        this.tracks = tracks;
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

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
