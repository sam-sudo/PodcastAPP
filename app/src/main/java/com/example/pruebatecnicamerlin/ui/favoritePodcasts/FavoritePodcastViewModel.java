package com.example.pruebatecnicamerlin.ui.favoritePodcasts;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebatecnicamerlin.io.podcastApi.PodcastApiAdapter;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastListResponse;
import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastResponse;
import com.example.pruebatecnicamerlin.ui.favoritePodcasts.interfaces.FavoritePodcastInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritePodcastViewModel extends ViewModel implements Callback<PodcastListResponse> {

    private MutableLiveData<ArrayList<PodcastResponse>> listMutableLiveData;
    private ArrayList<PodcastResponse> podcastRealArrayList = new ArrayList<>();

    private SharedPreferences sharedPreferences;

    private Context context;

    public LiveData<ArrayList<PodcastResponse>> getFavoriteList(Context context) {
        this.context = context;

        //todo condicionar que haya pasado 24 horas
        if(listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            initFavoriteTempList();
        }


        return listMutableLiveData;
    }

    public ArrayList<PodcastResponse> getFavoriteRealList() {

        return podcastRealArrayList;
    }

    public void initFavoriteTempList(){

        //rellenar con los 100 podcasts
        Call<PodcastListResponse> call = PodcastApiAdapter.getPodcastApiService().getPodcasts();
        call.enqueue(this);


    }

    public int getRealSize(){
        return podcastRealArrayList.size();
    }



    @Override
    public void onResponse(Call<PodcastListResponse> call, Response<PodcastListResponse> response) {

        if(response.isSuccessful()){
            PodcastListResponse podcastResponses = response.body();

            ArrayList<PodcastResponse> favoritePodcast = getFavoriteArrayList(podcastResponses.getFeed().getEntry());

            //TODO esto debería funcionar y quitar el parpadeo de refresco al volver del detalle a la lista de favoritos
            /*if(!listMutableLiveData.getValue().equals(favoritePodcast)){
                Log.d("TAG_navigate", "refreshFavoriteArrayList: resposne not equal");
                podcastRealArrayList = favoritePodcast;
                listMutableLiveData.setValue(favoritePodcast);

            }*/

            podcastRealArrayList = favoritePodcast;
            listMutableLiveData.setValue(favoritePodcast);

        }
    }

    @Override
    public void onFailure(Call<PodcastListResponse> call, Throwable t) {

    }


    private ArrayList<PodcastResponse> getFavoriteArrayList(ArrayList<PodcastResponse> arrayList) {



        ArrayList<PodcastResponse> favoriteArrayList = new ArrayList<>();


        for(PodcastResponse podcastResponse : arrayList){
            boolean isFavorite = isPodcastFavorite(podcastResponse.getId().getAttributes().getId());

            if(isFavorite){
                favoriteArrayList.add(podcastResponse);
            }
        }

        return favoriteArrayList;
    }

    public void refreshFavoriteArrayList() {
        initFavoriteTempList();
        ArrayList<PodcastResponse> favoriteArrayList = new ArrayList<>();


        for(PodcastResponse podcastResponse : podcastRealArrayList){
            boolean isFavorite = isPodcastFavorite(podcastResponse.getId().getAttributes().getId());

            if(isFavorite){
                favoriteArrayList.add(podcastResponse);
            }
        }

        /*for (int i = 0; i >= favoriteArrayList.size(); i++){
            listMutableLiveData.getValue()
        }*/

        if(null != listMutableLiveData && !listMutableLiveData.getValue().equals(favoriteArrayList)){
            this.listMutableLiveData.setValue(favoriteArrayList);

        }



    }

    public boolean isPodcastFavorite(String id) {


        sharedPreferences = context.getSharedPreferences("favorites", MODE_PRIVATE);

        String existId = sharedPreferences.getString(""+id, null);

        if( existId == null  ){
            return false;

        }
        return true;
    }


}