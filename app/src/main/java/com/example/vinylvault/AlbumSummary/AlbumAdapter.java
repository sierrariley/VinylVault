package com.example.vinylvault.AlbumSummary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.Pojo.Track;
import com.example.vinylvault.R;
import com.example.vinylvault.api.AlbumSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumSummaryViewHolder> {

    private ArrayList<Album> albums;
    private ArrayList<Track> tracks;
    private Context context;

    public AlbumAdapter(ArrayList<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }


    @NonNull
    @Override
    public AlbumSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_list_item, parent, false);
        return new AlbumSummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumSummaryViewHolder holder, int position) {
        Album album = albums.get(position);
        Track track = tracks.get(album.getId());

        holder.number.setText(position);
        holder.name.setText(track.getName());
//        holder.length.setText(track.getLength());

        //Make a new API Search
        //Would return array
        //https://itunes.apple.com/lookup?id=COLLECTIONID&entity=song
        String url =
                "https://itunes.apple.com/lookup?id=" +
                        album.getId() + "&entity=song";


        //Make a request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject mainObject = response.getJSONObject("results");
                            //Update the trackName
                            track.setName(mainObject.getString("trackName"));
                            AlbumDatabase db = new AlbumDatabase(context);
                            db.updateAlbum(album);
                            db.close();
                            Log.d("UPDATE", track.getName() + " TEMP UPDATED");
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
        AlbumSingleton.getInstance(context).getRequestQueue().add(request);
        //TODO: With collectionId from album, make new api search for track list
    }

    @Override
    public int getItemCount() {
        if(tracks != null){
            return tracks.size();
        }
        return 0;
    }

    class AlbumSummaryViewHolder extends RecyclerView.ViewHolder {
        protected TextView number;
        protected TextView name;
        protected TextView length;

        public AlbumSummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.number = itemView.findViewById(R.id.track_number);
            this.name = itemView.findViewById(R.id.track_name);
            this.length = itemView.findViewById(R.id.track_length);
        }
    }
}
