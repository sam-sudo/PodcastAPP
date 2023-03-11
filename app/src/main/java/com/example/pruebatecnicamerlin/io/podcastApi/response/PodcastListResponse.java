package com.example.pruebatecnicamerlin.io.podcastApi.response;

import com.google.gson.annotations.SerializedName;

public class PodcastListResponse {

    @SerializedName("feed")
    private FeedResponse feed;

    public PodcastListResponse(FeedResponse feed) {
        this.feed = feed;
    }

    public FeedResponse getFeed() {
        return feed;
    }

    public void setFeed(FeedResponse feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "PodcastListResponse{" +
                "feed=" + feed +
                '}';
    }


}
