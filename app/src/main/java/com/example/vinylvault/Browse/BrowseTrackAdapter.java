package com.example.vinylvault.Browse;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.AlbumSummary.AlbumAdapter;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.Pojo.Track;
import com.example.vinylvault.R;
import java.util.ArrayList;

/**
 * Author: Sage
 */
public class BrowseTrackAdapter extends RecyclerView.Adapter<BrowseTrackAdapter.BrowseTrackViewHolder> {

    private ArrayList<Track> tracks;

    public BrowseTrackAdapter(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public void setTracks(ArrayList<Track> newTracks) {
        if (tracks == null) {
            tracks = new ArrayList<>();
        }
        tracks.addAll(newTracks);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BrowseTrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_list_item, parent, false);
        return new BrowseTrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseTrackViewHolder holder, int position) {
        Track track = tracks.get(position);

        holder.number.setText(String.valueOf(position + 1) + ".");
        holder.name.setText(track.getName());
        holder.length.setText(track.getLength());
        Log.d("RV_TRACK_NAME " + position, track.getName());
    }

    @Override
    public int getItemCount() {
        if(tracks != null){
            return tracks.size();
        }
        return 0;
    }

    class BrowseTrackViewHolder extends RecyclerView.ViewHolder {
        protected TextView number;
        protected TextView name;
        protected TextView length;

        public BrowseTrackViewHolder(@NonNull View itemView) {
            super(itemView);
            this.number = itemView.findViewById(R.id.track_number);
            this.name = itemView.findViewById(R.id.track_name);
            this.length = itemView.findViewById(R.id.track_length);
        }
    }
}
