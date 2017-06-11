package com.master.movie.moviemaster.data;

import android.util.Log;

import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.internal.ApiService;
import com.master.movie.moviemaster.internal.ApiServiceWrapper;

import java.util.ArrayList;

/**
 * Created by stefa on 6/4/2017.
 */

public class MainMovieListModel {
    private ArrayList<Movie> movies;
    ApiServiceWrapper apiServiceWrapper;

    public MainMovieListModel(ApiServiceWrapper apiServiceWrapper) {
        this.apiServiceWrapper = apiServiceWrapper;
        initializeMovies();
    }

    private void initializeMovies() {
        apiServiceWrapper.dummyMethod();
        movies = new ArrayList<>();
        movies.add(new Movie(1, "The Shawshank Redemption", 1994, 9.2f));
        movies.add(new Movie(2, "The Godfather", 1972, 9.2f));
        movies.add(new Movie(3, "The Godfather: Part II", 1974, 9.0f));
        movies.add(new Movie(4, "The Dark Knight", 1972, 8.9f));
        movies.add(new Movie(5, "12 Angry Men", 1957, 8.9f));
        movies.add(new Movie(6, "Schindler's List", 1993, 8.9f));
        movies.add(new Movie(7, "Pulp Fiction", 1994, 8.9f));
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

}
