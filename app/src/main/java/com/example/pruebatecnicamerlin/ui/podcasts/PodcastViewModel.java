package com.example.pruebatecnicamerlin.ui.podcasts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class PodcastViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> listMutableLiveData;


    public LiveData<ArrayList<String>> getList() {

        if(listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            initTempList();
        }
        return listMutableLiveData;
    }


    public void initTempList(){
        //rellenar con los 100 podcasts
        //todo llamar a la api RETROFIT
        ArrayList list = new ArrayList();
        list.add("1--");
        list.add("2--");
        list.add("3--");
        listMutableLiveData.setValue(list);
    }

    public void addString(String string){
        if (listMutableLiveData.getValue() != null) {
            ArrayList<String> movieList = new ArrayList<>(listMutableLiveData.getValue());
            movieList.add(string);
            listMutableLiveData.setValue(movieList);
        }
    }



}