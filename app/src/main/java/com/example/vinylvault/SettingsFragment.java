package com.example.vinylvault;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.vinylvault.Database.AlbumDatabase;

/**
 * Author: Sierra
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        // Checkbox preference
        CheckBoxPreference deletePreference = findPreference("delete_vault");

        // Show Alert if checkbox is checked (true)
        deletePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean isChecked = (Boolean) newValue;
                if (isChecked) {
                    showDeleteAlert();
                }
                return true; // Return true to save change
            }
        });

    }

        // Delete Alert Message
        private void showDeleteAlert() {
            new AlertDialog.Builder(getContext())
                    .setTitle("Delete All Albums")
                    .setMessage("Are you sure you want to delete all Albums?")
                    .setIcon(R.drawable.ic_baseline_warning_amber_24)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Clear the Database
                            AlbumDatabase db = new AlbumDatabase(getContext());
                            db.clearDatabase();

                            //Give a Toast Notification
                            String toastString = "Albums Cleared";
                            int duration = Toast.LENGTH_SHORT;
                            Toast deleteToast = Toast.makeText(getContext(), toastString, duration);
                            deleteToast.show();

                            // Reset (uncheck) the Checkbox
                            CheckBoxPreference deletePreference = findPreference("delete_vault");
                            deletePreference.setChecked(false);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Uncheck the Checkbox if 'No' is pressed
                            CheckBoxPreference deletePreference = findPreference("delete_vault");
                            deletePreference.setChecked(false);
                        }
                    })
                    .show();
        }


    }




