package com.example.koracorner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class Share extends Fragment {




    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_share, container, false);

            //final Intent shareIntent = new Intent(Intent.ACTION_SEND);
            //shareIntent.setType("text/plain");
            //shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Kora Corner");
            //String shareMessage= "\nLet me recommend you this application for watching match :\n\n";
            //shareMessage = shareMessage + " https://kora1corner.blogspot.com/p/our-app.html \n\n";
          //  shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        //startActivity(Intent.createChooser(shareIntent, "choose one"));



         Button button = rootView.findViewById(R.id.share);



                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Let me recommend you this application for watching match :  ");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "Let me recommend you this application for watching match :   https://kora1corner.blogspot.com/p/our-app.html");
                        shareIntent.setType("text/plain");
                        startActivity(shareIntent);
                    }
                });




        return rootView;
    }









































}