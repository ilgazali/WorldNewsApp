package com.example.newsapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.MainActivity;
import com.example.newsapp.MainActivityViewModel;
import com.example.newsapp.model.MainNews;
import com.example.newsapp.model.ModelClass;
import com.example.newsapp.R;
import com.example.newsapp.adapter.Adapter;
import com.example.newsapp.restApi.ApiUtilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {

    String apiKey = "b5d9e80d15854aab9593cec3c7c36b38";
    private RecyclerView recyclerViewOfSports;
    Adapter adapter;
    ArrayList<ModelClass> modelClassArrayList;

    String category = "sports";
    MainActivity mainActivity;
    private MainActivityViewModel viewModel;

    public SportsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sports_fragment,null);

        mainActivity = (MainActivity)getActivity();

        modelClassArrayList = new ArrayList<>();

        recyclerViewOfSports = view.findViewById(R.id.recyclerViewOfSports);

        recyclerViewOfSports.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new Adapter(getContext(),modelClassArrayList);

        recyclerViewOfSports.setAdapter(adapter);

        viewModel.getCountry().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                Log.e("viewModel",s);

                findNews(s,category,100,apiKey);
                adapter.notifyDataSetChanged();


            }
        });
        final int[] state = new int[1];

        recyclerViewOfSports.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
    private void findNews(String country,String category, int pageSize,String apiKey) {

        ApiUtilities.getApiInterface().getCategoryNews(country,category,pageSize,apiKey).enqueue(new Callback<MainNews>() {
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
