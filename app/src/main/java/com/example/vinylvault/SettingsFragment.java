package com.example.vinylvault;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.vinylvault.Database.AlbumDatabase;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        AlbumDatabase db = new AlbumDatabase(getContext());
        //    Clear out saved albums
        SharedPreferences deletePreference = PreferenceManager.getDefaultSharedPreferences(getContext());
        Boolean deleteCheckBox = deletePreference.getBoolean("delete_vault", false);
        if(deleteCheckBox){
            new AlertDialog.Builder(getContext())
                    .setTitle("Delete All Albums")
                    .setMessage("Are you sure you want to delete all Albums?")
                    .setIcon(R.drawable.ic_baseline_warning_amber_24)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.clearDatabase();


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }

    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){

    }
}