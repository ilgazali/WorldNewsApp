package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.newsapp.adapter.PagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem mHome, mScience, mTech, mSports, mEntertainment, mHealth;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;

    public RelativeLayout mMainToolbarLayout;

    String apiKey = "12c0646cee1f428bbf6964a755f71afd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMainToolbarLayout = findViewById(R.id.main_toolbar_layout);

        tabLayout = findViewById(R.id.tablayout);

        mHome = findViewById(R.id.home);
        mScience = findViewById(R.id.science);
        mTech = findViewById(R.id.tecnology);
        mSports = findViewById(R.id.sports);
        mEntertainment = findViewById(R.id.entertainment);
        mHealth = findViewById(R.id.health);


        viewPager = findViewById(R.id.fragmentConteiner);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),6);


        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1 ||tab.getPosition() == 2
                        ||tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5){
                    pagerAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}