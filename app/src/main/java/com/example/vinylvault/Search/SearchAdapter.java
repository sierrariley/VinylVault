package com.example.vinylvault.Search;

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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<Album> albums;
    private Context context;

    public SearchAdapter(ArrayList<Album> albums,Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.search_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}