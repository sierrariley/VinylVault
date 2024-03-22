package com.example.vinylvault.Pojo;

public class Artist {
    private int id;
    private String name;

    //Constructor
    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //No args
    public Artist() {
    }

    //Getters and Setters
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


    //ToString
    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                '}';
    }
}
