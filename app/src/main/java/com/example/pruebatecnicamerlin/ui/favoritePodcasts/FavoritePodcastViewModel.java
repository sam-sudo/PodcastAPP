package com.example.pruebatecnicamerlin.ui.favoritePodcasts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritePodcastViewModel extends ViewModel {

    private final MutableLiveData<List> listMutableLiveData;

    public FavoritePodcastViewModel() {
        listMutableLiveData = new MutableLiveData<>();

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("hola 1");
        arrayList.add("hola 2");
        arrayList.add("hola 3");

        listMutableLiveData.setValue(arrayList);
    }

    public LiveData<List> getText() {
        return listMutableLiveData;
    }
}