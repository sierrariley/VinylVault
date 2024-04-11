package com.example.vinylvault.Browse;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.vinylvault.AddAnAlbumFragment;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import com.example.vinylvault.api.AlbumSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Author: Sage
 */
public class BrowseVPAdapter extends FragmentStateAdapter {

    private final Context context;
    private final ArrayList<Album> albums = new ArrayList<>();

    private final String[] randomTerms = {
            "Taylor Swift",
            "Beyonce",
            "Mac Miller",
            "The 1975",
            "Justin Bieber",
            "Paramore",
            "Doja Cat",
            "Djo",
            "Noah Kahan",
            "SZA",
            "The Weeknd",
            "Dua Lipa",
            "Olivia Rodrigo",
            "Drake",
            "Kanye",
            "Kid Cudi",
            "Jack Harlow",
            "Future",
            "Mitski",
            "Ariana Grande"
    };
    private Random rand = new Random();

    public BrowseVPAdapter(@NonNull FragmentActivity fragmentActivity, Context context) {
        super(fragmentActivity);
        this.context = context;
        //Fetch albums when adapter is called
        fetchAlbumData();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position < albums.size()) {
            return BrowseFragment.newInstance(albums.get(position));
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        //Change this once cases are updated.
        return albums.size();
    }

    public String createURL(){
        Log.d("RANDOM_INT", randomTerms[rand.nextInt(randomTerms.length - 1)]);
        String url = "https://itunes.apple.com/search?" +
                "country=CA&" +
                "media=album&" +
                "term=" + randomTerms[rand.nextInt(randomTerms.length - 1)] +
                "&entity=album";

        return url;
    }

    private void fetchAlbumData(){
        // Make a network request to fetch album data
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, createURL(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultsArray = response.getJSONArray("results");

                            for (int i = 0; i < resultsArray.length(); i++) {
                                JSONObject albumObject = resultsArray.getJSONObject(i);

                                // Album Storing
                                Album album = new Album();
                                String albumName = albumObject.getString("collectionName");
                                String albumArtwork = albumObject.getString("artworkUrl100");
                                String albumArtist = albumObject.getString("artistName");
                                String albumGenre = albumObject.getString("primaryGenreName");

                                album.setName(albumName);
                                album.setArtwork(albumArtwork);
                                album.setArtistName(albumArtist);
                                album.setGenre(albumGenre);

                                // Track List Storing
                                String albumCollectionId = albumObject.getString("collectionId");
                                album.setCollectionId("https://itunes.apple.com/lookup?id=" + albumCollectionId + "&entity=song");

                                albums.add(album);
                            }

                            // Notify the adapter that data set has changed
                            notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY_ERROR", error.getLocalizedMessage());
            }
        });

        // Add the request to the request queue
        AlbumSingleton.getInstance(context).getRequestQueue().add(request);
    }
}
