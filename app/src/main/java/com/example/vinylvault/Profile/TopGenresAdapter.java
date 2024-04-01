package com.example.vinylvault.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.Pojo.Genre;
import com.example.vinylvault.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopGenresAdapter extends RecyclerView.Adapter<TopGenresAdapter.TopGenreViewHolder>{

    private ArrayList<Genre> genres;
    private Context context;

    public TopGenresAdapter(ArrayList<Genre> genres, Context context) {
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
        Genre genre = genres.get(position);
        holder.name.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        if(genres != null){
            return genres.size();
        }
        return 5;
    }

    class TopGenreViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;

        public TopGenreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.genre_name);
        }
    }
}
