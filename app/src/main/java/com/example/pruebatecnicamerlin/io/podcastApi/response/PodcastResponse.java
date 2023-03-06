package com.example.pruebatecnicamerlin.io.podcastApi.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;

public class PodcastResponse {

    @SerializedName("im:name")
    private Name name;

    @SerializedName("im:image")
    private ArrayList<Image> image;

    @SerializedName("summary")
    private Summary summary;

    @SerializedName("id")
    private Id id;

    @SerializedName("im:artist")
    private Artist artist;

    @SerializedName("im:releaseDate")
    private ReleaseDate releaseDate;

    @SerializedName("category")
    private Category category;

    public PodcastResponse(Name name, ArrayList<Image> image, Summary summary, Id id, Artist artist, ReleaseDate releaseDate, Category category) {
        this.name = name;
        this.image = image;
        this.summary = summary;
        this.id = id;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PodcastResponse that = (PodcastResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public ArrayList<Image> getImage() {
        return image;
    }

    public void setImage(ArrayList<Image> image) {
        this.image = image;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ReleaseDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "PodcastResponse{" +
                "name=" + name +
                ", image=" + image +
                ", summary=" + summary +
                ", id=" + id +
                ", artist=" + artist +
                ", releaseDate=" + releaseDate +
                ", category=" + category +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    //------------internal classes---------------

    public class Name{

        @SerializedName("label")
        private String label;


        public Name(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "Name{" +
                    "label='" + label + '\'' +
                    '}';
        }
    }

    public class Image{

        @SerializedName("label")
        private String label;

        public Image(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "Image{" +
                    "label='" + label + '\'' +
                    '}';
        }
    }

    public class Summary{

        @SerializedName("label")
        private String label;

        public Summary(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "Summary{" +
                    "label='" + label + '\'' +
                    '}';
        }
    }

    public class Id {
        @SerializedName("label")
        private String label;

        @SerializedName("attributes")
        private Attributes attributes;

        public Id(String label, Attributes attributes) {
            this.label = label;
            this.attributes = attributes;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "label='" + label + '\'' +
                    ", attributes='" + attributes + '\'' +
                    '}';
        }

        public class Attributes{
            @SerializedName("im:id")
            private String id;

            public Attributes(String id) {
                this.id = id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "Attributes{" +
                        "id='" + id + '\'' +
                        '}';
            }
        }
    }

    public class Artist{
        @SerializedName("label")
        private String label;


        public Artist(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return "Artist{" +
                    "label='" + label + '\'' +
                    '}';
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public class ReleaseDate{
        @SerializedName("attributes")
        private Attributes attributes;

        public ReleaseDate(Attributes label) {
            this.attributes = label;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }


        @Override
        public String toString() {
            return "ReleaseDate{" +
                    "label=" + attributes +
                    '}';
        }

        public class Attributes{
            @SerializedName("label")
            private String label;

            public Attributes(String label) {
                this.label = label;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            @Override
            public String toString() {
                return "Attributes{" +
                        "label='" + label + '\'' +
                        '}';
            }
        }



    }

    public class Category{
        @SerializedName("attributes")
        private Attributes attributes;

        public Category(Attributes attributes) {
            this.attributes = attributes;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "attributes=" + attributes +
                    '}';
        }

        public class Attributes{
            @SerializedName("term")
            private String term;

            public Attributes(String term) {
                this.term = term;
            }

            public String getTerm() {
                return term;
            }

            public void setTerm(String term) {
                this.term = term;
            }

            @Override
            public String toString() {
                return "Attributes{" +
                        "term='" + term + '\'' +
                        '}';
            }
        }
    }
}
