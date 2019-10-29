package com.example.koracorner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends Fragment {
    RecyclerView mRecyclerView;
    BottomNavigationView bottomNavigationView;
    FrameLayout viewlayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);









        viewlayout = (FrameLayout)view.findViewById(R.id.viewlayout);
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);

        final FragmentManager fragmentManager = getFragmentManager();

        // Declare fragments here
        final Home home = new Home();
        final Channel msg = new Channel();
        final Table video = new Table();




        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                fragment = video;
                                getActivity().setTitle(item.getTitle());
                                break;
                            case R.id.action_item3:
                                fragment = msg;
                                getActivity().setTitle(item.getTitle());
                                break;

                            case R.id.action_item2:
                            default:
                                fragment = home;
                                getActivity().setTitle(item.getTitle());
                                break;

                        }
                        fragmentManager.beginTransaction().replace(R.id.viewlayout, fragment).commit();
                        return true;
                    }
                });




        // Set default selection

        bottomNavigationView.setSelectedItemId(R.id.action_item2);



        return view;
    }

}