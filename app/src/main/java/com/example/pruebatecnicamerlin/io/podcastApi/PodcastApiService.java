package com.example.pruebatecnicamerlin.io.podcastApi;

import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastListResponse;
import com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail.PodcastDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PodcastApiService {

    @GET("/us/rss/toppodcasts/limit=40/genre=1310/json")
    Call<PodcastListResponse> getPodcasts();

    @GET("https://itunes.apple.com/lookup?id={id}&entity=podcastEpisode&limit=9")
    Call<PodcastDetailResponse> getPodcastDetail(@Path("id") String id);

}
