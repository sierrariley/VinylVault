package com.example.vinylvault.Search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;
import com.example.vinylvault.api.AlbumSingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<Album> albums;
    private Context context;
    private ArrayList<Album> searchedAlbums;
    private SearchView searchView;
    private String query;


    public SearchAdapter(Context context) {
        this.context = context;
//        this.albums = albums;
//        this.searchedAlbums = new ArrayList<>(albums);
//        this.query = query;
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Album album = searchedAlbums.get(position);
        holder.albumName.setText(album.getName());
        holder.artistName.setText(album.getArtistName());

        Picasso.get().load(album.getArtwork()).into(holder.image);

            // Construct the API URL with the search query
            String url = "https://itunes.apple.com/search?" +
                    "country=CA&" +
                    "media=album&" +
                    "term=" + query +
                    "&entity=album";
            System.out.println(url);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray mainArray = response.getJSONArray("results");
                                JSONObject mainObject = mainArray.getJSONObject(0);
                                album.setName(mainObject.getString("collectionName"));
                                album.setArtistName(mainObject.getString("artistName"));
                                album.setArtwork(mainObject.getString("artworkUrl60"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VOLLEY_ERROR", error.getLocalizedMessage());
                }
            });
            AlbumSingleton.getInstance(context).getRequestQueue().add(request);

        }




    @Override
    public int getItemCount() {
        if(searchedAlbums != null){
            return searchedAlbums.size();
        }
       return  0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;
        protected TextView albumName;
        protected TextView artistName;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.search_item_image);
            this.albumName = itemView.findViewById(R.id.albumName);
            this.artistName = itemView.findViewById(R.id.artistName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }


}
