package com.example.vinylvault.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class AlbumArtist implements Parcelable {
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

    protected AlbumArtist(Parcel in) {
        id = in.readInt();
        album_id = in.readInt();
        artist_id = in.readInt();
    }

    public static final Creator<AlbumArtist> CREATOR = new Creator<AlbumArtist>() {
        @Override
        public AlbumArtist createFromParcel(Parcel in) {
            return new AlbumArtist(in);
        }

        @Override
        public AlbumArtist[] newArray(int size) {
            return new AlbumArtist[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(album_id);
        dest.writeInt(artist_id);
    }
}
