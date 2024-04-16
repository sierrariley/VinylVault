package com.example.vinylvault;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Author: Sierra
 */
public class CreditsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credits, container, false);
        Button email = view.findViewById(R.id.emailButton);

        //Email intent to support
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "support@vinylvault.com";
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL, email);
                startActivity(i);
            }
        });

        return view;
    }
}