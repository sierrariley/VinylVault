package com.example.vinylvault.AlbumSummary;

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
import com.example.vinylvault.R;

//TODO: Will contain fab button that then opens AddAnAblum
public class AlbumSummaryFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ALBUM_ID = "param1";
    private static final String ARG_ALBUM_NAME = "param2";
    private static final String ARG_ALBUM_IMAGE = "param3";
    private static final String ARG_ALBUM_ARTIST = "param4";
    private static final String ARG_ALBUM_GENRE = "param5";

    private int mAlbum;
    private String mName;
    private int mImage;
    private String mArtist;
    private String mGenre;

    public AlbumSummaryFragment(){}

    public static AlbumSummaryFragment newInstance(int album, String name, int image, String artist, String genre){
        AlbumSummaryFragment fragment = new AlbumSummaryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ALBUM_ID, album);
        args.putString(ARG_ALBUM_NAME, name);
        args.putInt(ARG_ALBUM_IMAGE, image);
        args.putString(ARG_ALBUM_ARTIST, artist);
        args.putString(ARG_ALBUM_GENRE, genre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAlbum = getArguments().getInt(ARG_ALBUM_ID);
            mName = getArguments().getString(ARG_ALBUM_NAME);
            mImage = getArguments().getInt(ARG_ALBUM_IMAGE);
            mArtist = getArguments().getString(ARG_ALBUM_ARTIST);
            mGenre = getArguments().getString(ARG_ALBUM_GENRE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_summary, container, false);

        ImageView album = view.findViewById(R.id.album_image);
        TextView album_name = view.findViewById(R.id.album_name);
        TextView artist = view.findViewById(R.id.album_artist_name);
        TextView genre = view.findViewById(R.id.album_genre);

        if (mName != null && mImage != 0 && mArtist != null && mGenre != null) {
            album.setImageResource(mImage);
            album_name.setText(mName);
            artist.setText(mArtist);
            genre.setText(mGenre);
        }

        ImageView delete = view.findViewById(R.id.album_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Delete this from the database
            }
        });



        RecyclerView trackList = view.findViewById(R.id.album_track_list);

        AlbumDatabase db = new AlbumDatabase(getContext());
        AlbumAdapter adapter = new AlbumAdapter(db.getTracksByAlbum(mAlbum), getContext());
        trackList.setAdapter(adapter);
        trackList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}