package com.example.vinylvault.Browse;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.vinylvault.AddAnAlbumFragment;
import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.Pojo.Track;
import com.example.vinylvault.R;
import com.example.vinylvault.api.AlbumSingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Author: Sage
 */
public class BrowseFragment extends Fragment {

    public static final String ALBUM = "album";

    private Album album;
    TextView albumName, artistName, genre;
    ImageView image;
    RecyclerView recyclerView;
    BrowseTrackAdapter adapter;
    FloatingActionButton fabButton;
    ArrayList<Track> tracks;

    public static BrowseFragment newInstance(Album album) {
        Bundle args = new Bundle();
        BrowseFragment fragment = new BrowseFragment();
        args.putParcelable(ALBUM, album);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            album = getArguments().getParcelable(ALBUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse, container, false);

        albumName = view.findViewById(R.id.browse_album_name);
        artistName = view.findViewById(R.id.browse_artist_name);
        genre = view.findViewById(R.id.browse_genre);
        image = view.findViewById(R.id.browse_album_image);
        recyclerView = view.findViewById(R.id.browse_tracks);
        fabButton = getActivity().findViewById(R.id.fab);

        adapter = new BrowseTrackAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (album != null) {
            album = getArguments().getParcelable(ALBUM);

            Log.d("PASSED_ALBUM", album.getName());

            albumName.setText(album.getName());
            artistName.setText(album.getArtistName());
            genre.setText(album.getGenre());
                String modifiedURL = album.getArtwork().replace("100x100bb.jpg", "400x400bb.jpg");
                Picasso.get().load(modifiedURL).placeholder(R.drawable.album_placeholder).error(R.drawable.album_error_placeholder).into(image);

            //Make a request
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, album.getCollectionId(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ALBUM_COLLECTION_URL", album.getCollectionId());
                            Log.d("RESPONSE_JSON", response.toString());

                            tracks = new ArrayList<>();
                            JSONArray trackArray = response.getJSONArray("results");

                            for (int i = 1; i < trackArray.length(); i++){
                                JSONObject trackObject = trackArray.getJSONObject(i);
                                String trackName = trackObject.getString("trackName");

                                //Convert milliseconds to formatted minutes
                                int lengthTemp = trackObject.getInt("trackTimeMillis");
                                int minutes = lengthTemp / (1000 * 60);
                                int seconds = (lengthTemp / 1000) % 60;
                                String trackLength = String.format("%d:%02d", minutes, seconds);

                                Track track = new Track();
                                track.setName(trackName);
                                track.setLength(trackLength);
                                track.setAlbum(album);
                                tracks.add(track);
                            }

                            //Sends to adapter
                            adapter.setTracks(tracks);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY_ERROR", error.getLocalizedMessage());
                    }
                }
            );

            fabButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle extra = new Bundle();
                    extra.putParcelable(AddAnAlbumFragment.ALBUM, album);

                    AlbumDatabase db = new AlbumDatabase(getContext());

                    if (db.getAlbum(album.getId()) != null) {
                        //Update
                        extra.putInt(AddAnAlbumFragment.ACTION_TYPE, AddAnAlbumFragment.UPDATE);
                    } else {
                        //Create
                        extra.putInt(AddAnAlbumFragment.ACTION_TYPE, AddAnAlbumFragment.CREATE);
                    }
                    Navigation.findNavController(view).navigate(R.id.nav_add_album, extra);
                }
            });

            AlbumSingleton.getInstance(getContext()).getRequestQueue().add(request);
        }

        return view;
    }
}