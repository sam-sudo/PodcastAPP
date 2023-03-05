package com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PodcastDetailListResponse {

    @SerializedName("resultCount")
    private String resultCount;

    @SerializedName("results")
    private ArrayList<PodcastDetailResponse> results;

    public PodcastDetailListResponse(String resultCount, ArrayList<PodcastDetailResponse> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }


    @Override
    public String toString() {
        return "PodcastDetailListResponse{" +
                "resultCount='" + resultCount + '\'' +
                ", results=" + results +
                '}';
    }

    public ArrayList<PodcastDetailResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<PodcastDetailResponse> results) {
        this.results = results;
    }
}
