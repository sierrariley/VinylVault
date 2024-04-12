package com.example.vinylvault.Profile;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.R;
import com.google.android.material.textview.MaterialTextView;

/**
 * Used for the ProfilePage - aka HomePage
 * A simple {@link Fragment} subclass.
 * Author: Sage
 */
public class ProfileFragment extends Fragment {

    RecyclerView topAlbumsRV, topGenresRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        NavOptions options = new NavOptions.Builder().setExitAnim(R.anim.enter_in).build();

        TextView currentlyListening = view.findViewById(R.id.profile_currently_listening);
        currentlyListening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_currently_listening, null, options);
            }
        });

        TextView toListen = view.findViewById(R.id.profile_to_listen);
        toListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_profile_to_nav_to_listen, null, options);
            }
        });

        topAlbumsRV = view.findViewById(R.id.profile_top_albums);
        topGenresRV = view.findViewById(R.id.profile_top_genres);

        AlbumDatabase db = new AlbumDatabase(getContext());

        TopAlbumsAdapter topAlbumsAdapter = new TopAlbumsAdapter(db.getTopAlbums(5), getContext());
        topAlbumsRV.setAdapter(topAlbumsAdapter);
        topAlbumsRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        TopGenresAdapter topGenreAdapter = new TopGenresAdapter(db.getAllGenres(), getContext());
        topGenresRV.setAdapter(topGenreAdapter);
        topGenresRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }
}