package com.example.vinylvault.Browse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.AlbumSummary.AlbumAdapter;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import java.util.ArrayList;

public class BrowseTrackAdapter extends RecyclerView.Adapter<BrowseTrackAdapter.BrowseTrackViewHolder> {

    private ArrayList<Album> albums;
    private Context context;

    public BrowseTrackAdapter(ArrayList<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }


    @NonNull
    @Override
    public BrowseTrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_list_item, parent, false);
        return new BrowseTrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseTrackViewHolder holder, int position) {
        //TODO: With collectionId from album, make new api search for track list
    }

    @Override
    public int getItemCount() {
        //TODO: Length of Array provided with track info
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
