package com.example.vinylvault.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Artist implements Parcelable {
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

    protected Artist(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

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
        return getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}
