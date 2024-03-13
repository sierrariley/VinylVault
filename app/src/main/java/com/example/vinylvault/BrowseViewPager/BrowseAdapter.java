package com.example.vinylvault.BrowseViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BrowseAdapter extends FragmentStateAdapter {

    public BrowseAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            //TODO: Add cases
            default:
                //TODO: Edit this to return a default case
                //return BrowseFragment.newInstance();
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
