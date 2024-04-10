package com.example.vinylvault.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Track implements Parcelable {
    private int id;
    private String name;
    private String length;
    private Album album;
    private String mp3;

    public Track(int id, String name, String length, Album album, String mp3) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.album = album;
        this.mp3 = mp3;

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
        mp3 = in.readString();
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

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(length);
        dest.writeParcelable(album, flags);
        dest.writeString(mp3);
    }
}
