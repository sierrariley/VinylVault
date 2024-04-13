package com.example.vinylvault.CurrentlyListening;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;

import java.util.ArrayList;

/**
 * Author: Sage
 *  + minor additions from Sierra (Shared Preferences)
 */
public class CurrentlyFragment extends Fragment {

    RecyclerView recyclerView;
    CurrentlyAdapter adapter;
    ArrayList<Album> currentListenAlbums;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currently, container, false);

        recyclerView = view.findViewById(R.id.currently_listening_recycler_view);
        adapter = new CurrentlyAdapter(new ArrayList<>(), getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        AlbumDatabase db = new AlbumDatabase(getContext());
        currentListenAlbums = new ArrayList<>();

        for (Album album : db.getAllAlbums()) {
            if (album.getStatus() == 1) {
                currentListenAlbums.add(album);
            }
        }

        CurrentlyAdapter adapter = new CurrentlyAdapter(currentListenAlbums, getContext());
        recyclerView.setAdapter(adapter);

        //Settings: If changing view layout based on user choice in settings
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Boolean gridPreference = preferences.getBoolean("grid_view", true);
        if(gridPreference){
            SharedPreferences rowPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String selectedValue = rowPreferences.getString("grid_rows", "3");
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), Integer.parseInt(selectedValue)));
        }else{
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }


        return view;
    }


}