package com.example.vinylvault.AlbumSummary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.Pojo.Track;
import com.example.vinylvault.R;
import com.example.vinylvault.api.AlbumSingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//TODO: Will contain fab button that then opens AddAnAlbum
public class AlbumSummaryFragment extends Fragment {

    public static final String ALBUM = "album";
    public static final String TRACKS = "tracks";


    Album album;
    ImageView image, delete;
    TextView album_name, artist, genre;
    RecyclerView trackList;
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
        delete = view.findViewById(R.id.album_delete);
        trackList = view.findViewById(R.id.album_track_list);

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
                    });
            AlbumSingleton.getInstance(getContext()).getRequestQueue().add(request);
        }

        //TODO: Do we need this delete button here? - maybe add a long click option somewhere
        delete.setVisibility(View.INVISIBLE);
        //Delete the selected album from the database
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete " + album.getName() + "?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlbumDatabase db = new AlbumDatabase(getContext());
                                db.deleteAlbum(album.getId());
                                db.close();
                            }
                        })
                        .setNegativeButton("No", null) //don't do anything
                        .show();
            }
        });

        return view;
    }
}