package com.example.vinylvault.Browse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinylvault.R;

/**
 * ViewPager Host
 */
public class BrowseViewPagerHostFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse_view_pager_host, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.browseViewPager);
        viewPager2.setAdapter(new BrowseAdapter(getActivity()));
        //TODO: Add  a transformer
//        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        return view;
    }
}