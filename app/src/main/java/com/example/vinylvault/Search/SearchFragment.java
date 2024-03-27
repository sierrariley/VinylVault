package com.example.vinylvault.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchView searchView;
    private ArrayList<Album> albumArrayList;
    private SearchAdapter searchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView searchRV = view.findViewById(R.id.search_recycler_view);
        albumArrayList = new ArrayList<>();
        searchView = view.findViewById(R.id.search_searchBar);
        searchView.clearFocus();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        AlbumDatabase db = new AlbumDatabase(getContext());
        searchAdapter = new SearchAdapter(db.getAllAlbums(), getContext());
        searchRV.setAdapter(searchAdapter);
        searchRV.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return view;
    }

    private void searchList(String text) {
        ArrayList<Album> searchList = new ArrayList<>();
        for(Album album : albumArrayList){
            if(album.getName().toLowerCase().contains(text.toLowerCase())){
                searchList.add(album);
            }

            if(searchList.isEmpty()){
//                int duration = Toast.LENGTH_SHORT;
//                Toast toast = Toast.makeText(this, "No Album Found", duration);
//                toast.show();
            }else{
                searchAdapter.setSearchList(searchList);
            }
        }
    }
}