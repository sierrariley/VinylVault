package com.example.vinylvault.Search;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

    RecyclerView recyclerView;
    SearchView searchView;
    private SearchAdapter searchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.search_searchBar);
        recyclerView = view.findViewById(R.id.search_recycler_view);

        // Set up RecyclerView and adapter
        searchAdapter = new SearchAdapter(new ArrayList<>(), getContext());
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

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
                                ArrayList<String> trackURLS = new ArrayList<>();
                                JSONArray resultsArray = response.getJSONArray("results");

                                for (int i = 0; i < resultsArray.length(); i++) {
                                    JSONObject albumObject = resultsArray.getJSONObject(i);

                                    //Album Storing
                                    String albumName = albumObject.getString("collectionName");
                                    String albumArtwork = albumObject.getString("artworkUrl100");
                                    Album album = new Album(albumName, albumArtwork);
                                    String albumArtist = albumObject.getString("artistName");
                                    String albumGenre = albumObject.getString("primaryGenreName");
                                    album.setArtistName(albumArtist);
                                    album.setGenre(albumGenre);

                                    //Tracklist Storing
                                    String albumCollectionId = albumObject.getString("collectionId");
                                    album.setCollectionId("https://itunes.apple.com/lookup?id=" + albumCollectionId + "&entity=song");

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
