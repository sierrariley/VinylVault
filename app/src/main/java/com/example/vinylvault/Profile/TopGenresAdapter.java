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

public class TopGenresAdapter extends RecyclerView.Adapter<TopGenresAdapter.TopGenreViewHolder>{

    private ArrayList<Album> albums;
    private Context context;

    public TopGenresAdapter(ArrayList<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public TopGenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new TopGenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopGenreViewHolder holder, int position) {
        //TODO
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class TopGenreViewHolder extends RecyclerView.ViewHolder{
        protected ImageView image;

        public TopGenreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.profile_item_image);
        }
    }
}
