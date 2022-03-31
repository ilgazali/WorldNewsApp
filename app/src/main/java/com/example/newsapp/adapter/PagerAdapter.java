package com.example.newsapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapp.fragment.BusinessFragment;
import com.example.newsapp.fragment.EntertainmentFragment;
import com.example.newsapp.fragment.HealthFragment;
import com.example.newsapp.fragment.HomeFragment;
import com.example.newsapp.fragment.ScienceFragment;
import com.example.newsapp.fragment.SportsFragment;
import com.example.newsapp.fragment.TecnologyFragment;

public class PagerAdapter extends FragmentPagerAdapter {
//class for tab layout and fragment passes

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new HomeFragment();
            case 1:
                return new SportsFragment();
            case 2:
                return new HealthFragment();
            case 3:
                return new ScienceFragment();
            case 4:
                return new BusinessFragment();
            case 5:
                return new EntertainmentFragment();
            case 6:
                return new TecnologyFragment();
            default:
                return null;
        }

    }
    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}
