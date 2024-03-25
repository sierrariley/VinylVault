package com.example.vinylvault.CurrentlyListening;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;

import java.util.ArrayList;

public class CurrentlyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currently, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.currently_listening_recycler_view);

        AlbumDatabase db = new AlbumDatabase(getContext());
        ArrayList<Album> currentListenAlbums = new ArrayList<>();
        for (Album album : db.getAllAlbums()) {
            if (album.getStatus() == 1) {
                currentListenAlbums.add(album);
            }
        }
        CurrentlyAdapter adapter = new CurrentlyAdapter(currentListenAlbums, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return view;
    }
}