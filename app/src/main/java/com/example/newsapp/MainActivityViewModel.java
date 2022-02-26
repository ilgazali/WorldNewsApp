package com.example.newsapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> country ;

    public MainActivityViewModel() {
        country = new MutableLiveData<String>("tr");
    }

    public MutableLiveData<String> getCountry() {
        return country;
    }


    public void updateData(String string){
        country.setValue(string);
    }

}
