package com.example.vinylvault.AlbumSummary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinylvault.AddAnAlbumFragment;
import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import com.squareup.picasso.Picasso;

//TODO: Will contain fab button that then opens AddAnAlbum
public class AlbumSummaryFragment extends Fragment {

    public static final String ALBUM = "album";
    Album album;
    ImageView image, delete;
    TextView album_name, artist, genre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_summary, container, false);

        image = view.findViewById(R.id.album_image);
        album_name = view.findViewById(R.id.album_name);
        artist = view.findViewById(R.id.album_artist_name);
        genre = view.findViewById(R.id.album_genre);
        delete = view.findViewById(R.id.album_delete);

        if (getArguments() != null) {
            album = getArguments().getParcelable(ALBUM);
            Picasso.get().load(album.getArtwork()).error(R.drawable.album_error_placeholder).into(image);
            album_name.setText(album.getName());
            artist.setText(album.getArtistName());
            genre.setText(album.getGenre());
        }

        //Delete the selected album from the database
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete " + album.getName() + "?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlbumDatabase db = new AlbumDatabase(getContext());
                                db.deleteAlbum(album.getId());
                                db.close();
                            }
                        })
                        .setNegativeButton("No", null) //don't do anything
                        .show();
            }
        });

        RecyclerView trackList = view.findViewById(R.id.album_track_list);
        AlbumDatabase db = new AlbumDatabase(getContext());
        AlbumAdapter adapter = new AlbumAdapter(db.getTracksByAlbum(album.getId()), getContext());
        trackList.setAdapter(adapter);
        trackList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}