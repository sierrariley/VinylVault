package com.example.vinylvault.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Album implements Parcelable {
    private int id;
    private String name;
    private String artistName;
    private String genre;
    private String artwork;
    private int rating;
    private int status;  // 1 = Listening to, 2 = To listen to, 3 = Finished


    public Album(int id, String name, String artistName, String genre, String artwork, int rating, int status) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.genre = genre;
        this.artwork = artwork;
        this.rating = rating;
        this.status = status;
    }

    public Album(String name, String artwork) {
        this.name = name;
        this.artwork = artwork;
    }

    public Album() {
    }

    protected Album(Parcel in) {
        id = in.readInt();
        name = in.readString();
        artistName = in.readString();
        genre = in.readString();
        artwork = in.readString();
        rating = in.readInt();
        status = in.readInt();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public Album(String albumName, String artistName, String imageUrl) {
        this.name = albumName;
        this.artistName = artistName;
        this.artwork = imageUrl;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.artistName);
        dest.writeString(this.genre);
        dest.writeString(this.artwork);
        dest.writeInt(this.rating);
        dest.writeInt(this.status);
    }

    public void readFromParcel(Parcel source){
        this.id = source.readInt();
        this.name = source.readString();
        this.artistName = source.readString();
        this.genre = source.readString();
        this.artwork = source.readString();
        this.rating = source.readInt();
        this.status = source.readInt();
    }

}
