package com.example.vinylvault.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: Sierra
 */
public class Track implements Parcelable {
    private int id;
    private String name;
    private String length;
    private Album album;

    public Track(int id, String name, String length, Album album) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.album = album;
    }

    public Track(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Track() {
    }


    protected Track(Parcel in) {
        id = in.readInt();
        name = in.readString();
        length = in.readString();
        album = in.readParcelable(Album.class.getClassLoader());
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(length);
        parcel.writeParcelable(album, i);
    }
}
