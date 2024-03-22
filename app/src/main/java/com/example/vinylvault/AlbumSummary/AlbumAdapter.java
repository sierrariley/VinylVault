package com.example.vinylvault.AlbumSummary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumSummaryViewHolder> {

    //TODO: Finish this file - will need the database file
    private ArrayList<Album> albums;
    private Context context;

    public AlbumAdapter(ArrayList<Album> albums, Context context) {
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

    }

    @Override
    public int getItemCount() {
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
