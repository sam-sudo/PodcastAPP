package com.example.pruebatecnicamerlin.io.podcastApi.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedResponse {


    @SerializedName("entry")
    private ArrayList<PodcastResponse> entry;

    public FeedResponse(ArrayList<PodcastResponse> entry) {
        this.entry = entry;
    }

    public ArrayList<PodcastResponse> getEntry() {
        return entry;
    }

    public void setEntry(ArrayList<PodcastResponse> entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "FeedResponse{" +

                ", entry=" + entry +
                '}';
    }
}
