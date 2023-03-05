package com.example.pruebatecnicamerlin.io.podcastApi.response.podcastDetail;

import com.google.gson.annotations.SerializedName;

public class PodcastDetailResponse {

    @SerializedName("collectionId")
    private String collectionId;

    @SerializedName("artistName")
    private String artistName;

    @SerializedName("shortDescription")
    private String description;

    @SerializedName("trackName")
    private String trackName;

    @SerializedName("artworkUrl60")
    private String artworkUrl60;

    @SerializedName("trackCount")
    private String trackCount;

    @SerializedName("releaseDate")
    private String releaseDate;

    @SerializedName("trackTimeMillis")
    private String trackTimeMillis;

    @SerializedName("episodeUrl")
    private String episodeUrl;

    public PodcastDetailResponse(String collectionId, String artistName, String description, String trackName, String artworkUrl60, String trackCount, String releaseDate, String trackTimeMillis, String episodeUrl) {
        this.collectionId = collectionId;
        this.artistName = artistName;
        this.description = description;
        this.trackName = trackName;
        this.artworkUrl60 = artworkUrl60;
        this.trackCount = trackCount;
        this.releaseDate = releaseDate;
        this.trackTimeMillis = trackTimeMillis;
        this.episodeUrl = episodeUrl;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(String trackCount) {
        this.trackCount = trackCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(String trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEpisodeUrl() {
        return episodeUrl;
    }

    public void setEpisodeUrl(String episodeUrl) {
        this.episodeUrl = episodeUrl;
    }

    @Override
    public String toString() {
        return "PodcastDetailResponse{" +
                "collectionId='" + collectionId + '\'' +
                ", artistName='" + artistName + '\'' +
                ", description='" + description + '\'' +
                ", trackName='" + trackName + '\'' +
                ", artworkUrl60='" + artworkUrl60 + '\'' +
                ", trackCount='" + trackCount + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", trackTimeMillis='" + trackTimeMillis + '\'' +
                ", episodeUrl='" + episodeUrl + '\'' +
                '}';
    }

}
