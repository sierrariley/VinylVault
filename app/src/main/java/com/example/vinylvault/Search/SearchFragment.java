package com.example.vinylvault.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import com.example.vinylvault.api.AlbumSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ArrayList<Album> albumArrayList;
    private  SearchAdapter searchAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.search_searchBar);
        recyclerView = view.findViewById(R.id.search_recycler_view);

        // Set up RecyclerView and adapter
        searchAdapter = new SearchAdapter(new ArrayList<>(), getContext());
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // Set up SearchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Construct the API URL with the search query
                String url = "https://itunes.apple.com/search?" +
                        "country=CA&" +
                        "media=album&" +
                        "term=" + query +
                        "&entity=album";


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    ArrayList<Album> albumList = new ArrayList<>();
                                    JSONArray resultsArray = response.getJSONArray("results");

                                    for (int i = 0; i < resultsArray.length(); i++) {
                                        JSONObject albumObject = resultsArray.getJSONObject(i);
                                        String albumName = albumObject.getString("collectionName");
                                        String albumArtwork = albumObject.getString("artworkUrl60");

                                        Album album = new Album(albumName, albumArtwork);
                                        albumList.add(album);
                                    }
                                    //Sends to adapter
                                    searchAdapter.setAlbums(albumList);
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
                AlbumSingleton.getInstance(getContext()).getRequestQueue().add(request);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return view;
    }





}
