package com.example.vinylvault.Profile;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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

    ImageView currentlyListening, toListen;
    RecyclerView topAlbumsRV, topGenresRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        currentlyListening = view.findViewById(R.id.filler);
        currentlyListening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_currently_listening);
            }
        });

        toListen = view.findViewById(R.id.filler2);
        toListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_profile_to_nav_to_listen);
            }
        });

        topAlbumsRV = view.findViewById(R.id.profile_top_albums);
        topGenresRV = view.findViewById(R.id.profile_top_genres);

        AlbumDatabase db = new AlbumDatabase(getContext());

        TopAlbumsAdapter topAlbumsAdapter = new TopAlbumsAdapter(db.getTopAlbums(5), getContext());
        topAlbumsRV.setAdapter(topAlbumsAdapter);
        topAlbumsRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        TopGenresAdapter topGenreAdapter = new TopGenresAdapter(db.getTopGenres(5), getContext());
        topGenresRV.setAdapter(topGenreAdapter);
        topGenresRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }
}