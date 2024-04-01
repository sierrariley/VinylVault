package com.example.vinylvault.CurrentlyListening;

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

public class CurrentlyAdapter extends RecyclerView.Adapter<CurrentlyAdapter.CurrentlyViewHolder> {

    private ArrayList<Album> albums;
    private Context context;

    public CurrentlyAdapter(ArrayList<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public CurrentlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new CurrentlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentlyViewHolder holder, int position) {
        Album album = albums.get(position);
//        holder.image.setImageResource(album.getArtwork());
        //Picasso.get().load(LINK TO ARTWORK).placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder_error).into(imageView);

    }

    @Override
    public int getItemCount() {
        if(albums != null){
            return albums.size();
        }
        return 0;
    }

    class CurrentlyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;

        public CurrentlyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.search_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Navigation.findNavController(view).navigate(R.id.nav_album_summary);
        }
    }
}
