package com.example.pruebatecnicamerlin.ui.podcasts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebatecnicamerlin.io.podcastApi.PodcastApiAdapter;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastListResponse;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PodcastViewModel extends ViewModel implements Callback<PodcastListResponse> {

    private MutableLiveData<ArrayList<PodcastResponse>> listMutableLiveData;


    public LiveData<ArrayList<PodcastResponse>> getList() {

        if(listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            initTempList();
        }
        return listMutableLiveData;
    }


    public void initTempList(){

        //rellenar con los 100 podcasts
        Call<PodcastListResponse> call = PodcastApiAdapter.getPodcastApiService().getPodcasts();
        call.enqueue(this);


    }


    @Override
    public void onResponse(Call<PodcastListResponse> call, Response<PodcastListResponse> response) {
        Log.d("TAG", "onResponse: ");
        if(response.isSuccessful()){
            PodcastListResponse podcastResponses = response.body();
            Log.d("TAG", "onResponse: " + podcastResponses.toString());

            ArrayList<PodcastResponse> list = new ArrayList<>();

            list.addAll(podcastResponses.getFeed().getEntry());

            listMutableLiveData.setValue(list);

        }
    }

    @Override
    public void onFailure(Call<PodcastListResponse> call, Throwable t) {

    }
}