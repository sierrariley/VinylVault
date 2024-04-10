package com.example.vinylvault;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

/**
 * Author: Sierra
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}