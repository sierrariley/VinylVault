package com.example.vinylvault.Vault;

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

public class VaultAdapter extends RecyclerView.Adapter<VaultAdapter.VaultViewHolder> {

    private ArrayList<Album> albums;
    private Context context;

    public VaultAdapter(ArrayList<Album> albums,Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public VaultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new VaultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaultViewHolder holder, int position) {
        Album album = albums.get(position);
        //Only display if the album status == listened to
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

    class VaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;

        public VaultViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.search_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //TODO: Open up album summary
        }
    }
}
