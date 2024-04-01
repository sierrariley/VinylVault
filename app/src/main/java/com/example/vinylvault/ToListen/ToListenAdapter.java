package com.example.vinylvault.ToListen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;

import java.util.ArrayList;

public class ToListenAdapter extends RecyclerView.Adapter<ToListenAdapter.ToListenViewHolder> {

    private ArrayList<Album> albums;
    private Context context;

    public ToListenAdapter(ArrayList<Album> albums,Context context) {
        this.albums = albums;
        this.context = context;
    }


    @NonNull
    @Override
    public ToListenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new ToListenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToListenViewHolder holder, int position) {
        Album album = albums.get(position);
        //TODO: Figure out how to set an image resource to an api image link
//        holder.image.setImageResource(album.getArtwork());
    }

    @Override
    public int getItemCount() {
        if(albums != null){
            return albums.size();
        }
        return 0;
    }

    class ToListenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;

        public ToListenViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.search_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //TODO: Pass in a bundle
            Navigation.findNavController(view).navigate(R.id.nav_album_summary);
        }
    }
}
