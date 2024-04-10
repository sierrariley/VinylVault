package com.example.vinylvault.Profile;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item, parent, false);
        return new TopGenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopGenreViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.name.setText(album.getGenre());
    }

    @Override
    public int getItemCount() {
        if (albums.size() != 0){
            return albums.size();
        } else {
            albums.add(new Album("Add Albums!"));
            return albums.size();
        }
    }

    class TopGenreViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;

        public TopGenreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.genre_name);
        }
    }
}
