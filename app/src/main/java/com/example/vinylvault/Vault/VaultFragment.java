package com.example.vinylvault.Vault;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

public class VaultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vault, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.vault_recycler_view);

        AlbumDatabase db = new AlbumDatabase(getContext());
        ArrayList<Album> vaultAlbums = new ArrayList<>();
        for (Album album : db.getAllAlbums()) {
            if (album.getStatus() == 3) {
                vaultAlbums.add(album);
            }
        }

        VaultAdapter adapter = new VaultAdapter(vaultAlbums, getContext());
        recyclerView.setAdapter(adapter);

        //Settings: If changing view layout based on user choice in settings
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Boolean gridPreference = preferences.getBoolean("grid_view", true);
        if(gridPreference){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }else{
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        //Clear out saved albums
        SharedPreferences deletePreference = PreferenceManager.getDefaultSharedPreferences(getContext());
        Boolean deleteCheckBox = deletePreference.getBoolean("delete_vault", false);
        if(!deleteCheckBox){
            new AlertDialog.Builder(getContext())
                    .setTitle("Delete All Albums")
                    .setMessage("Are you sure you want to delete all Albums?")
                    .setIcon(R.drawable.ic_baseline_warning_amber_24)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //TODO: Delete all albums in vault code
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }


        return view;
    }
}