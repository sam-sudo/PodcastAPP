package com.example.pruebatecnicamerlin.ui.podcastDetail;

import android.util.Log;
import android.widget.ImageButton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebatecnicamerlin.io.podcastApi.PodcastApiAdapter;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastListResponse;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastResponse;
import com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail.PodcastDetailListResponse;
import com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail.PodcastDetailResponse;
import com.example.pruebatecnicamerlin.ui.podcastDetail.interfaces.PodcastDetailInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PodcastDetailViewModel extends ViewModel implements Callback<PodcastDetailListResponse> {

    private MutableLiveData<ArrayList<PodcastDetailResponse>> listMutableLiveData;
    private ArrayList<PodcastDetailResponse> podcastResponseArrayList = new ArrayList<>();


    public LiveData<ArrayList<PodcastDetailResponse>> getDetailList(String id) {
        //todo condicionar que haya pasado 24 horas
        if(listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            initTempList(id);
        }
        return listMutableLiveData;
    }

    public void initTempList(String id){

        //rellenar con los 100 podcasts
        Call<PodcastDetailListResponse> call =
                PodcastApiAdapter.getPodcastApiService()
                        .getPodcastDetail(id,"podcastEpisode",9);
        call.enqueue(this);


    }

    @Override
    public void onResponse(Call<PodcastDetailListResponse> call, Response<PodcastDetailListResponse> response) {
        Log.d("TAG", "onResponse: ");
        if(response.isSuccessful()){
            PodcastDetailListResponse podcastResponses = response.body();
            Log.d("TAG", "onResponse: " + podcastResponses.toString());


            podcastResponseArrayList = podcastResponses.getResults();
            listMutableLiveData.setValue(podcastResponses.getResults());

        }
    }

    @Override
    public void onFailure(Call<PodcastDetailListResponse> call, Throwable t) {

    }


}