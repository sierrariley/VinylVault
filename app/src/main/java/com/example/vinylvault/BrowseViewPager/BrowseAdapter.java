package com.example.vinylvault.BrowseViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vinylvault.R;

public class BrowseAdapter extends FragmentStateAdapter {

    public BrowseAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            //TODO: Add the proper cases
            case 0:
                return BrowseFragment.newInstance("Album Name 0", "Artist Name", "Genre", R.drawable.album_placeholder, "null");
            case 1:
                return BrowseFragment.newInstance("Album Name 1", "Artist Name", "Genre", R.drawable.album_placeholder, "null");
            default:
                return BrowseFragment.newInstance("Album Name", "Artist Name", "Genre", R.drawable.album_placeholder, "null");
        }
    }

    @Override
    public int getItemCount() {
        //Change this once cases are updated.
        return 2;
    }
}
