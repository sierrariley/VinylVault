package com.example.vinylvault;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.squareup.picasso.Picasso;

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
        currentlyListening = view.findViewById(R.id.add_album_radio1);
        toListen = view.findViewById(R.id.add_album_radio2);
        done = view.findViewById(R.id.add_album_radio3);
        submit = view.findViewById(R.id.add_album_button);

        if (getArguments() != null) {
            if (getArguments().getInt(ACTION_TYPE) == UPDATE) {
                album = getArguments().getParcelable(ALBUM);
                Picasso.get().load(album.getArtwork()).error(R.drawable.album_error_placeholder).into(image);
                artist.setText(album.getArtistName());
                albumName.setText(album.getName());
                genre.setText(album.getGenre());
                seekBar.setProgress(album.getRating());
                review.setText(album.getReview());
                submit.setText("Update Album");

            } else if (getArguments().getInt(ACTION_TYPE) == CREATE) {
                album = new Album();
                submit.setText("Add Album");
            }
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissKeyboard(view);

                if (done.isChecked()) album.setStatus(3);
                if (currentlyListening.isChecked()) album.setStatus(1);
                if (toListen.isChecked()) album.setStatus(2);

                album.setRating(seekBar.getProgress());
                album.setReview(review.getText().toString());

                AlbumDatabase db = new AlbumDatabase(getContext());
                if (getArguments().getInt(ACTION_TYPE) == UPDATE) {
                    db.updateAlbum(album);
                } else if (getArguments().getInt(ACTION_TYPE) == CREATE) {
                    db.addAlbum(album);
                }
                db.close();

                Navigation.findNavController(view).popBackStack();
            }
        });

        return view;
    }

    /**
     * Dismiss the keyboard
     * @param view - View
     */
    public void dismissKeyboard(View view){
        if(view.requestFocus()){
            InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}