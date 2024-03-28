package com.example.vinylvault.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;

import java.util.ArrayList;

public class TopAlbumsAdapter extends RecyclerView.Adapter<TopAlbumsAdapter.TopAlbumsViewHolder> {

    private ArrayList<Album> albums;
    private Context context;

    public TopAlbumsAdapter(ArrayList<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }
    @NonNull
    @Override
    public TopAlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new TopAlbumsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAlbumsViewHolder holder, int position) {
        //TODO
    }

    @Override
    public int getItemCount() {
        //Only displays top 5 albums, needs the rating or order by
        return 5;
    }

    class TopAlbumsViewHolder extends RecyclerView.ViewHolder{
        protected ImageView image;

        public TopAlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.profile_item_image);
        }
    }
}
