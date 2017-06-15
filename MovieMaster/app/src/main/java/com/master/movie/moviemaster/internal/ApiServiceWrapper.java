package com.master.movie.moviemaster.internal;

import android.util.Log;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.master.movie.moviemaster.dto.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by stefan.bacevic on 6/11/2017.
 */

public class ApiServiceWrapper {
    private ApiService apiService;

    public ApiServiceWrapper(ApiService apiService) {
        this.apiService = apiService;
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            Response<Movie[]> movieResponse = apiService.getAllMovies("/all").execute();
            if (movieResponse.isSuccessful()) {
                movies.addAll(new ArrayList<>(Arrays.asList(movieResponse.body())));
            } else {
                Log.e("ERROR", "bad response body");
            }
        } catch (IOException e) {
            handleError(e);
        }
        return movies;
    }

    private void handleError(IOException e){
        if (e instanceof JsonMappingException) {
            Log.e("ERROR", "JSON Mapping Problem");
        } else {
            Log.e("ERROR", e.getLocalizedMessage());
        }
    }
}
