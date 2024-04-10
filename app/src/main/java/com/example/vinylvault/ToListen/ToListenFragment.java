package com.example.vinylvault.ToListen;

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

public class ToListenFragment extends Fragment {

    RecyclerView recyclerView;
    ToListenAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_listen, container, false);

        recyclerView = view.findViewById(R.id.to_listen_recycler_view);
        adapter = new ToListenAdapter(new ArrayList<>(), getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        AlbumDatabase db = new AlbumDatabase(getContext());
        ArrayList<Album> toListenAlbums = new ArrayList<>();
        for (Album album : db.getAllAlbums()) {
            if (album.getStatus() == 2) {
                toListenAlbums.add(album);
            }
        }

    adapter.setAlbums(toListenAlbums);

        return view;
    }
}