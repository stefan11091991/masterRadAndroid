package com.master.movie.moviemaster.dto;

/**
 * Created by stefan.bacevic on 6/7/2017.
 */

public class Movie {
    int id;
    String name;
    int year;
    float rating;

    public Movie(int id, String name, int year, float rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
