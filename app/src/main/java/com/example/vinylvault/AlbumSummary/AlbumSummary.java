package com.example.vinylvault.AlbumSummary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinylvault.R;

public class AlbumSummary extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_summary, container, false);

        ImageView album = view.findViewById(R.id.album_image);
        ImageView delete = view.findViewById(R.id.album_delete);
        TextView album_name = view.findViewById(R.id.album_name);
        TextView artist = view.findViewById(R.id.album_artist_name);
        TextView genre = view.findViewById(R.id.album_genre);
        RecyclerView trackList = view.findViewById(R.id.album_track_list);


        //TODO: Database creation + set layout adapter
        /*
        Albums db = new Albums(getContext());
        AlbumAdapter adapter = new AlbumAdapter(db.getAlbums(), getContext());
        trackList.setAdapter(adapter);
        trackList.setLayoutManager(new LinearLayoutManager(getContext()));
        */

        return view;
    }
}