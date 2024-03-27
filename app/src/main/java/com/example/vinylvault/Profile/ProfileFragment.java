package com.example.vinylvault.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;

/**
 * Used for the ProfilePage - aka HomePage
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //TODO: FOR SAGE - delete username and pfp section. add currently listening button and to listen to

        RecyclerView topAlbumsRV = view.findViewById(R.id.profile_top_albums);
        RecyclerView topGenresRV = view.findViewById(R.id.profile_top_genres);

        AlbumDatabase db = new AlbumDatabase(getContext());

        /**
         * TODO: Two different adapters
         * one takes in all albums and displays the top 5
         * second one takes in genres and displays the top 5 genres
         */
//        ProfileAdapter topAlbumsAdapter = new ProfileAdapter(db.getAllAlbums(), getContext());
//        topAlbumsRV.setAdapter(topAlbumsAdapter);
//        topAlbumsRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

//        ProfileAdapter topGenreAdapter = new ProfileAdapter(db.getAllAlbums(), getContext());
//        topGenresRV.setAdapter(topGenreAdapter);
//        topGenresRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        return view;
    }
}