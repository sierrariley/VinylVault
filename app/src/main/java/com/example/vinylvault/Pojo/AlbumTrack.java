package com.example.vinylvault.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class AlbumTrack implements Parcelable {
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

    protected AlbumTrack(Parcel in) {
        id = in.readInt();
        album_id = in.readInt();
        track_id = in.readInt();
    }

    public static final Creator<AlbumTrack> CREATOR = new Creator<AlbumTrack>() {
        @Override
        public AlbumTrack createFromParcel(Parcel in) {
            return new AlbumTrack(in);
        }

        @Override
        public AlbumTrack[] newArray(int size) {
            return new AlbumTrack[size];
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

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(album_id);
        dest.writeInt(track_id);
    }
}
