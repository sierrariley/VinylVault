package com.example.vinylvault.Search;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.vinylvault.AlbumSummary.AlbumSummaryFragment;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.Pojo.Track;
import com.example.vinylvault.R;
import com.example.vinylvault.api.AlbumSingleton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<Album> albums;
    private Context context;

    public SearchAdapter(ArrayList<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged(); // Notify adapter of dataset changes
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Album album = albums.get(position);
        Picasso.get().load(album.getArtwork()).error(R.drawable.album_error_placeholder).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extra = new Bundle();
                //TODO: Also pass that it's new -> delete button should not appear
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

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.profile_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }



}
