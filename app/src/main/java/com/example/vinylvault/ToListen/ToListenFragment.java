package com.example.vinylvault.ToListen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.R;

public class ToListenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_listen, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.to_listen_recycler_view);


        AlbumDatabase db = new AlbumDatabase(getContext());
        ToListenAdapter adapter = new ToListenAdapter(db.getAllAlbums(), getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return view;
    }
}