package com.example.vinylvault.Profile;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import com.squareup.picasso.Picasso;

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
        Album album = albums.get(position);
        Picasso.get()
                .load(album.getArtwork())
                .placeholder(R.drawable.album_placeholder)
                .error(R.drawable.profile_error_placeholder)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (albums.size() != 0){
            Log.d("ARRAY", String.valueOf(albums.size()));
            return albums.size();
        } else {
            albums.add(new Album("Add Albums!", "https://placehold.co/400x400"));
            Log.d("EMPTY_ARRAY", String.valueOf(albums.size()));
            return albums.size();
        }
    }

    class TopAlbumsViewHolder extends RecyclerView.ViewHolder{
        protected ImageView image;

        public TopAlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.profile_item_image);
        }
    }
}
