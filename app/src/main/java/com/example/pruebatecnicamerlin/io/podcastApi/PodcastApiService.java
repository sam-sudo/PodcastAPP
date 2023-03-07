package com.example.pruebatecnicamerlin.io.podcastApi;

import com.example.pruebatecnicamerlin.io.podcastApi.response.PodcastListResponse;
import com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail.PodcastDetailListResponse;
import com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail.PodcastDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PodcastApiService {

    @GET("/us/rss/toppodcasts/limit=100/genre=1310/json")
    Call<PodcastListResponse> getPodcasts();

    @GET("https://itunes.apple.com/lookup")
    Call<PodcastDetailListResponse> getPodcastDetail(
            @Query("id") String id,
            @Query("entity") String podcastEpisode,
            @Query("limit") int limit);

}
