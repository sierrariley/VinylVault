package com.example.vinylvault.Vault;

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

public class VaultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vault, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.vault_recycler_view);

        AlbumDatabase db = new AlbumDatabase(getContext());

        ArrayList<Album> vaultAlbums = new ArrayList<>();
        for (Album album : db.getAllAlbums()) {
            if (album.getStatus() == 1) {
                //TODO: Add to arrayList
                vaultAlbums.add(album);
            }
        }

        VaultAdapter adapter = new VaultAdapter(vaultAlbums, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return view;
    }
}