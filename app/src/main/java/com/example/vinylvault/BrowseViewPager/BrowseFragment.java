package com.example.vinylvault.BrowseViewPager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vinylvault.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrowseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrowseFragment extends Fragment {

    private static final String ARG_ALBUMNAME = "param1";
    private static final String ARG_ARTISTNAME = "param2";
    private static final String ARG_GENRE = "param3";
    private static final String ARG_IMAGE = "param4";
    public static final String ARG_TRACKLIST = "param5";

    private String albumNameParam;
    private String artistNameParam;
    private String genreParam;
    private int imageParam;
    //TODO: Replace this
    //Create a new class called TrackList that implements Parcelable
    //private TrackList trackListParam;
    private String trackListParam; //is this valid?

    public BrowseFragment() {}

    public static BrowseFragment newInstance(String albumNameParam, String artistNameParam, String genreParam, int imageParam, String trackListParam) {
        BrowseFragment fragment = new BrowseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ALBUMNAME, albumNameParam);
        args.putString(ARG_ARTISTNAME, artistNameParam);
        args.putString(ARG_GENRE, genreParam);
        args.putInt(ARG_IMAGE, imageParam);
        args.putString(ARG_TRACKLIST, trackListParam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            albumNameParam = getArguments().getString(ARG_ALBUMNAME);
            artistNameParam = getArguments().getString(ARG_ARTISTNAME);
            genreParam = getArguments().getString(ARG_GENRE);
            imageParam = getArguments().getInt(ARG_IMAGE);
            trackListParam = getArguments().getString(ARG_TRACKLIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse, container, false);
        if (albumNameParam != null && artistNameParam != null && genreParam != null && imageParam != 0){
            // && trackListParam != null
            TextView albumName = view.findViewById(R.id.browse_album_name);
            TextView artistName = view.findViewById(R.id.browse_artist_name);
            TextView genre = view.findViewById(R.id.browse_genre);
            ImageView image = view.findViewById(R.id.browse_album_image);
            ListView listView = view.findViewById(R.id.browse_tracks);


            albumName.setText(albumNameParam);
            artistName.setText(artistNameParam);
            genre.setText(genreParam);
            image.setImageResource(imageParam);
            /*
            TODO: Set adapter of ListView
            Will likely need to pass in values of tracks
             */
        }

        return view;
    }
}