package com.example.vinylvault.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.R;

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView searchRV = view.findViewById(R.id.search_recycler_view);

        AlbumDatabase db = new AlbumDatabase(getContext());
        SearchAdapter searchAdapter = new SearchAdapter(db.getAllAlbums(), getContext());
        searchRV.setAdapter(searchAdapter);
        searchRV.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return view;
    }
}