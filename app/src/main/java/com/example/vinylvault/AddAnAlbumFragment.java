package com.example.vinylvault;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.vinylvault.Pojo.Album;

/**
 * TODO: Opens based on fab button, If album status and fab button is hit...
 */
public class AddAnAlbumFragment extends Fragment {

    public static final int UPDATE = 1;
    public static final int CREATE = 2;

    public static final String ACTION_TYPE = "action_type";
    public static final String ALBUM = "album";

    Album album;
    ImageView image;
    TextView artist, albumName, genre;
    SeekBar seekBar;
    EditText review;
    RadioGroup radioGroup;
    RadioButton currentlyListening, done, toListen;
    Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_an_album, container, false);

        image = view.findViewById(R.id.add_album_image);
        artist = view.findViewById(R.id.add_album_artist);
        albumName = view.findViewById(R.id.add_album_album);
        genre = view.findViewById(R.id.add_album_genre);
        seekBar = view.findViewById(R.id.add_album_seek_bar);
        review = view.findViewById(R.id.add_album_review);
        radioGroup = view.findViewById(R.id.add_album_radio_group);
        currentlyListening = view.findViewById(R.id.add_album_radio1);
        toListen = view.findViewById(R.id.add_album_radio2);
        done = view.findViewById(R.id.add_album_radio3);
        submit = view.findViewById(R.id.add_album_button);

        switch (radioGroup.getCheckedRadioButtonId()) {
            case 0: //currently listening
                break;
            case 1: //to listen
                break;
            case 2: //done
                break;
        }


        if (done.isChecked()) {
            //do this
        } else if (currentlyListening.isChecked()) {
            //do this instead
        }


        if (getArguments() != null) {
            if (getArguments().getInt(ACTION_TYPE) == UPDATE) {
                album = getArguments().getParcelable(ALBUM);
                //Update Member (update database)

            } else if (getArguments().getInt(ACTION_TYPE) == CREATE) {
                album = new Album();
                //Create a new one (save to database)
            }
        }


        return view;
    }
}