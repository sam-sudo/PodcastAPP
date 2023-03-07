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
    private ArrayList<PodcastResponse> podcastResponseArrayList = new ArrayList<>();
    ArrayList<String> genders = new ArrayList<>();

    public LiveData<ArrayList<PodcastResponse>> getList() {
        //todo condicionar que haya pasado 24 horas
        if(listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            initTempList();
        }
        return listMutableLiveData;
    }

    public int getRealSize(){
        return podcastResponseArrayList.size();
    }
    public int getGendersSize(){
        return podcastResponseArrayList.size();
    }


    public void initTempList(){

        //rellenar con los 100 podcasts
        Call<PodcastListResponse> call = PodcastApiAdapter.getPodcastApiService().getPodcasts();
        call.enqueue(this);


    }


    @Override
    public void onResponse(Call<PodcastListResponse> call, Response<PodcastListResponse> response) {

        if(response.isSuccessful()){
            PodcastListResponse podcastResponses = response.body();

            podcastResponseArrayList = podcastResponses.getFeed().getEntry();
            listMutableLiveData.setValue(podcastResponses.getFeed().getEntry());

        }
    }

    @Override
    public void onFailure(Call<PodcastListResponse> call, Throwable t) {

    }


    public ArrayList<String> getTypeOfGenders(ArrayList<PodcastResponse> arrayList){

        if(!genders.contains("All")){
            genders.add("All");
        }

        for(PodcastResponse podcastResponse : arrayList){
            String gender = podcastResponse.getCategory().getAttributes().getTerm();

            if(genders.contains(gender)){
                continue;
            }

            genders.add(gender);

        }


        return genders;
    }

    public void updateList(String param){

        ArrayList<PodcastResponse> podcastResponseList = new ArrayList<>();

        if(param.length() > 0){
            for(PodcastResponse podcastResponse : podcastResponseArrayList){


                String title = podcastResponse.getName().getLabel();
                String author = podcastResponse.getArtist().getLabel();

                Log.d("TAG", "getList: title " + title);
                if(title.toUpperCase().startsWith(param.toUpperCase()) || author.toUpperCase().startsWith(param.toUpperCase())){
                    podcastResponseList.add(podcastResponse);
                }



            }

            listMutableLiveData.setValue(podcastResponseList);
        }else {
            listMutableLiveData.setValue(podcastResponseArrayList);
        }



    }
    public void updateListByGender(String param){

        ArrayList<PodcastResponse> podcastResponseList = new ArrayList<>();

        if(param.equals("All")){
            listMutableLiveData.setValue(podcastResponseArrayList);
            return;
        }

        if(param.length() > 0){
            for(PodcastResponse podcastResponse : podcastResponseArrayList){


                String gender = podcastResponse.getCategory().getAttributes().getTerm();

                if(gender.equalsIgnoreCase(param)){
                    podcastResponseList.add(podcastResponse);
                }



            }

            listMutableLiveData.setValue(podcastResponseList);
        }else {
            listMutableLiveData.setValue(podcastResponseArrayList);
        }



    }
}