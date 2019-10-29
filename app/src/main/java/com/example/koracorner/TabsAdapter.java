package com.example.koracorner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by tutlane on 19-12-2017.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Home home = new Home();
                return home;
            case 1:
                Channel channel = new Channel();
                return channel;
            case 2:
                Table table = new Table();
                return table;
            default:
                return null;
        }
    }
}