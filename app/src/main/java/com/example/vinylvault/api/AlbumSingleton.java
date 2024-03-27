package com.example.vinylvault.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AlbumSingleton {

    public static AlbumSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private AlbumSingleton(Context context) {
        this.context = context;
    }

    /**
     *
     * @param context
     * @return instance
     */
    public static AlbumSingleton getInstance(Context context){
        if(instance == null){
            instance = new AlbumSingleton(context);
        }
        return instance;
    }

    /**
     *
     * @return requestQueue
     */
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}
