package com.example.vinylvault.Profile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylvault.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

//    private ArrayList<Albums> albums;
    private Context context;
/*
    public ProfileAdapter(ArrayList<Album> locations,Context context) {
        this.context = context;
    }
*/
    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected ImageView image;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.profile_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if it's a genre - open up that page
            //if it's an album - open up that page
        }
    }
}
