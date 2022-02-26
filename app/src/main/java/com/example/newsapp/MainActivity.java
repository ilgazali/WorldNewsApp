package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.newsapp.adapter.Adapter;
import com.example.newsapp.adapter.PagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.hbb20.CountryCodePicker;


import java.util.Locale;

public class MainActivity extends AppCompatActivity  {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem mHome, mScience, mTech, mSports, mEntertainment, mHealth;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;



    public String country;
    CountryCodePicker countryCodePicker;

    public RelativeLayout mMainToolbarLayout;

    String apiKey = "b5d9e80d15854aab9593cec3c7c36b38";



    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        countryCodePicker = findViewById(R.id.ccp);

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


        countryCodePicker.setAutoDetectedCountry(true);
        countryCodePicker.setCustomMasterCountries("ae,ar,at,au,be,bg,br" +
                ",ca,ch,cn,co,cu,cz,de,eg,fr,gb,gr,hk" +
                ",hu,id,ie,il,in,it,jp,kr,lt,lv,ma" +
                ",mx,my,ng,nl,no,nz,ph,pl,pt,ro," +
                "rs,ru,sa,se,sg,si,sk,th,tr,tw,ua,us,ve,za");
        countryCodePicker.setDefaultCountryUsingNameCode("tr");
        countryCodePicker.resetToDefaultCountry();
        country = countryCodePicker.getSelectedCountryNameCode().toLowerCase(Locale.ROOT);


        viewModel.updateData(country);

        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country = countryCodePicker.getSelectedCountryNameCode().toLowerCase(Locale.ROOT);

                viewModel.updateData(country);

                viewPager.getAdapter().notifyDataSetChanged();

            }
        });




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