package com.example.vinylvault.Browse;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vinylvault.Pojo.Album;
import com.example.vinylvault.R;

public class BrowseVPAdapter extends FragmentStateAdapter {

    public BrowseVPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            //TODO: Add the proper cases based on a genre. Most common genre in database
            case 0:
                return BrowseFragment.newInstance(new Album());
            case 1:
                return BrowseFragment.newInstance(new Album());
            default:
                return BrowseFragment.newInstance(new Album());
        }
    }

    @Override
    public int getItemCount() {
        //Change this once cases are updated.
        return 2;
    }
}
