package com.example.vinylvault.Pojo;

public class AlbumTrack {
    private int id;
    private int album_id;
    private int track_id;

    public AlbumTrack(int id, int album_id, int track_id) {
        this.id = id;
        this.album_id = album_id;
        this.track_id = track_id;
    }

    public AlbumTrack() {
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

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }
}
