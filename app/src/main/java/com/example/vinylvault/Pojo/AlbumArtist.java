package com.example.vinylvault.Pojo;

public class AlbumArtist {
    private int id;
    private int album_id;
    private int artist_id;

    public AlbumArtist(int id, int album_id, int artist_id) {
        this.id = id;
        this.album_id = album_id;
        this.artist_id = artist_id;
    }

    public AlbumArtist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }
}
