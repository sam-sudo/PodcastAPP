package com.example.pruebatecnicamerlin.ui.podcastDetail.interfaces;

import android.widget.ImageButton;

import com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail.PodcastDetailResponse;

public interface PodcastDetailInterface {

    void getCountTracks(int countTracks);
    void startPauseTrack(String url, ImageButton imageButton);

}
