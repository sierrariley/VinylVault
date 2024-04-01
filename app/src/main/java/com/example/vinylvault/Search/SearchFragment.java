package com.example.vinylvault.Search;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vinylvault.Database.AlbumDatabase;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import com.example.vinylvault.api.AlbumSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        recyclerView = view.findViewById(R.id.search_recycler_view);
        searchView = view.findViewById(R.id.search_searchBar);
        albumArrayList = new ArrayList<>();



AlbumDatabase db = new AlbumDatabase(getContext());

        // Set up SearchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchString = newText;
                return true;

            }
        });

        db.addAlbum(new Album("Believe", "Justin Beiber", "https://is1-ssl.mzstatic.com/image/thumb/Music/54/7e/24/mzi.ehsdnggz.jpg/60x60bb.jpg"));


        // Set up RecyclerView and adapter
        searchAdapter = new SearchAdapter(getContext());
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }





}
