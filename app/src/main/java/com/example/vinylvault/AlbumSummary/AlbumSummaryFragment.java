package com.example.vinylvault.AlbumSummary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
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
import com.example.vinylvault.MainActivity;
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
public class AlbumSummaryFragment extends Fragment {

    public static final String ALBUM = "album";

    Album album;
    ImageView image;
    TextView album_name, artist, genre;
    RecyclerView trackList;
    FloatingActionButton fabButton;
    ArrayList<Track> tracks;
    private AlbumAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_summary, container, false);

        image = view.findViewById(R.id.album_image);
        album_name = view.findViewById(R.id.album_name);
        artist = view.findViewById(R.id.album_artist_name);
        genre = view.findViewById(R.id.album_genre);
        trackList = view.findViewById(R.id.album_track_list);
        fabButton = getActivity().findViewById(R.id.fab);

        adapter = new AlbumAdapter(new ArrayList<>(), getContext());
        trackList.setAdapter(adapter);
        trackList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null) {
            album = getArguments().getParcelable(ALBUM);

            // Resizes Album Image to be larger - API allows for this
            String modifiedURL = album.getArtwork().replace("100x100bb.jpg", "400x400bb.jpg");
            Picasso.get().load(modifiedURL).error(R.drawable.album_error_placeholder).into(image);
            album_name.setText(album.getName());
            artist.setText(album.getArtistName());
            genre.setText(album.getGenre());

            //Make a request
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, album.getCollectionId(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
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

                                    String trackMP3 = trackObject.getString("previewUrl");

                                    Track track = new Track();
                                    track.setName(trackName);
                                    track.setLength(trackLength);
                                    track.setAlbum(album);
                                    track.setMp3(trackMP3);
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
                    });

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