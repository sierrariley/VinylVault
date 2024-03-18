package com.example.vinylvault.CurrentlyListening;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vinylvault.R;

public class CurrentlyAdapter extends RecyclerView.Adapter<CurrentlyAdapter.CurrentlyViewHolder> {

    //TODO: Finish this file - will need the database file
//    private ArrayList<Albums> albums;
    private Context context;
    /*
        public CurrentlyAdapter(ArrayList<Album> albums,Context context) {
            this.context = context;
        }
    */

    @NonNull
    @Override
    public CurrentlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new CurrentlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentlyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
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

        }
    }
}
