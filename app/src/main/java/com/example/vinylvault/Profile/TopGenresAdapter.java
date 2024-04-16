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

    private ArrayList<String> genres;
    private Context context;

    public TopGenresAdapter(ArrayList<String> genres, Context context) {
        this.genres = genres;
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
        String album = genres.get(position);
        holder.name.setText(album);
    }

    @Override
    public int getItemCount() {
        if (genres.size() != 0){
            return genres.size();
        } else {
            genres.add("Add Albums!");
            return genres.size();
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
