package com.example.vinylvault.CurrentlyRecyclerView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinylvault.R;

public class CurrentlyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currently, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.currently_listening_recycler_view);

        //TODO: Database creation + set layout adapter
        /*
        Albums db = new Albums(getContext());
        CurrentlyAdapter adapter = new CurrentlyAdapter(db.getAlbums(), getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        */

        return view;
    }
}