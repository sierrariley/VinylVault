package com.example.vinylvault.ToListen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.AlbumSummary.AlbumSummaryFragment;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ToListenAdapter extends RecyclerView.Adapter<ToListenAdapter.ToListenViewHolder> {

    private ArrayList<Album> albums;
    private Context context;

    public ToListenAdapter(ArrayList<Album> albums,Context context) {
        this.albums = albums;
        this.context = context;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ToListenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new ToListenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToListenViewHolder holder, int position) {
        Album album = albums.get(position);
        Picasso.get()
                .load(album.getArtwork())
                .placeholder(R.drawable.album_placeholder)
                .error(R.drawable.album_error_placeholder)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extra = new Bundle();
                extra.putParcelable(AlbumSummaryFragment.ALBUM, album);
                Navigation.findNavController(view).navigate(R.id.nav_album_summary, extra);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(albums != null){
            return albums.size();
        }
        return 0;
    }

    class ToListenViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;

        public ToListenViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.profile_item_image);
        }

    }
}
