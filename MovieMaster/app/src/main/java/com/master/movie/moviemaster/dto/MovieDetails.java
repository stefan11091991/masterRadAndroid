package com.master.movie.moviemaster.dto;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stefan.bacevic on 7/22/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "year",
        "rating",
        "poster",
        "storyLine",
        "cast"
})
public class MovieDetails {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("year")
    private int year;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("poster")
    private String poster;
    @JsonProperty("storyLine")
    private String storyLine;
    @JsonProperty("cast")
    private List<String> cast = null;
    private Bitmap posterBitmap;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("year")
    public int getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty("rating")
    public double getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(double rating) {
        this.rating = rating;
    }

    @JsonProperty("poster")
    public String getPoster() {
        return poster;
    }

    @JsonProperty("poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    @JsonProperty("storyLine")
    public String getStoryLine() {
        return storyLine;
    }

    @JsonProperty("storyLine")
    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

    @JsonProperty("cast")
    public List<String> getCast() {
        return cast;
    }

    @JsonProperty("cast")
    public void setCast(List<String> cast) {
        this.cast = cast;
    }


    public Bitmap getPosterBitmap() {
        return posterBitmap;
    }

    public void setPosterBitmap(Bitmap posterBitmap) {
        this.posterBitmap = posterBitmap;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
