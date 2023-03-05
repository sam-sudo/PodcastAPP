package com.example.pruebatecnicamerlin.io.podcastApi;

import static com.example.pruebatecnicamerlin.util.Constans.PODCAST_BASE_URL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PodcastApiAdapter {

    private static PodcastApiService PODCAST_API_SERVICE;

    public static PodcastApiService getPodcastApiService(){

        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        if(PODCAST_API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(PODCAST_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            PODCAST_API_SERVICE =retrofit.create(PodcastApiService.class);
        }

        return PODCAST_API_SERVICE;

    }

}
