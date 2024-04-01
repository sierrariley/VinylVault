package com.example.vinylvault.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vinylvault.Database.AlbumDatabase;
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

//        ImageView currentlyListening = view.findViewById(R.id.profile_currently_listening);
//        currentlyListening.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO: Open CurrentlyListening Fragment
//            }
//        });
//
//        ImageView toListen = view.findViewById(R.id.profile_to_listen);
//        toListen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO: Open ToListen Fragment
//            }
//        });


        RecyclerView topAlbumsRV = view.findViewById(R.id.profile_top_albums);
        RecyclerView topGenresRV = view.findViewById(R.id.profile_top_genres);

        AlbumDatabase db = new AlbumDatabase(getContext());

        /**
         * TODO: Create TopGenre Adapter
         */
        TopAlbumsAdapter topAlbumsAdapter = new TopAlbumsAdapter(db.getAllAlbums(), getContext());
        topAlbumsRV.setAdapter(topAlbumsAdapter);
        topAlbumsRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        TopGenresAdapter topGenreAdapter = new TopGenresAdapter(db.getAllAlbums(), getContext());
        topGenresRV.setAdapter(topGenreAdapter);
        topGenresRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        return view;
    }
}