package com.example.vinylvault.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.vinylvault.Pojo.Track;

import java.util.ArrayList;

public class AlbumSingleton {

    public static AlbumSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;
    private ArrayList<Track> tracks; // Add tracks array


    private AlbumSingleton(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        tracks = new ArrayList<>(); // Initialize tracks array
    }

    /**
     *
     * @param context
     * @return instance
     */
    public static AlbumSingleton getInstance(Context context){
        if(instance == null){
            instance = new AlbumSingleton(context);
        }
        return instance;
    }

    /**
     *
     * @return requestQueue
     */
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    // Method to set tracks
    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    // Method to get tracks
    public ArrayList<Track> getTracks() {
        return tracks;
    }
}
