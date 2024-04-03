package com.example.vinylvault.Search;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ArrayList<Album> albumArrayList;
    private  SearchAdapter searchAdapter;
    private String searchString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.search_searchBar);
        recyclerView = view.findViewById(R.id.search_recycler_view);

//        albumArrayList = new ArrayList<>();


        AlbumDatabase db = new AlbumDatabase(getContext());
        // Set up RecyclerView and adapter
        searchAdapter = new SearchAdapter(new ArrayList<>(), "", getContext());
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up SearchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.setQuery(newText);
                searchAdapter.setAlbums(db.getAllAlbums());
                searchAdapter.notifyDataSetChanged();

                String url = "https://itunes.apple.com/search?" +
                        "country=CA&" +
                        "media=album&" +
                        "term=" + newText +
                        "&entity=album";
                System.out.println(url);

                return true;
            }

        });

//        db.addAlbum(new Album("Believe", "Justin Beiber", "https://is1-ssl.mzstatic.com/image/thumb/Music/54/7e/24/mzi.ehsdnggz.jpg/60x60bb.jpg"));

        return view;
    }





}
