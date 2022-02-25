package com.example.newsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.MainActivity;
import com.example.newsapp.model.MainNews;
import com.example.newsapp.model.ModelClass;
import com.example.newsapp.R;
import com.example.newsapp.adapter.Adapter;
import com.example.newsapp.restApi.ApiUtilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFragment extends Fragment {

    String apiKey = "12c0646cee1f428bbf6964a755f71afd";
    private RecyclerView recyclerViewOfHealth;
    Adapter adapter;
    ArrayList<ModelClass> modelClassArrayList;
    String country = "tr";
    String category = "health";

    MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_fragment,null);
        mainActivity = (MainActivity)getActivity();
        modelClassArrayList = new ArrayList<>();

        recyclerViewOfHealth = view.findViewById(R.id.recyclerViewOfHealth);

        recyclerViewOfHealth.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new Adapter(getContext(),modelClassArrayList);

        recyclerViewOfHealth.setAdapter(adapter);

        findNews();
        final int[] state = new int[1];

        recyclerViewOfHealth.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                state[0] = newState;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && (state[0] == 0 || state[0] == 2)){
                    hideToolbar();
                }else if (dy < -10){
                    showToolbar();
                }

            }
        });
        return view;

    }
    private void showToolbar() {
        mainActivity.mMainToolbarLayout.setVisibility(View.VISIBLE);
    }



    private void hideToolbar() {
        mainActivity.mMainToolbarLayout.setVisibility(View.GONE);
    }
    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,apiKey).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {

                modelClassArrayList.addAll(response.body().getArticles());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });



    }
}
