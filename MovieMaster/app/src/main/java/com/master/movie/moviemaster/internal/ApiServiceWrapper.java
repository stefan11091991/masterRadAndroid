package com.master.movie.moviemaster.internal;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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

    public Movie getPoster(Movie movie) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Constants.rootUrl + movie.getPoster())
                .build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            if(!response.isSuccessful()){
                Log.d("ERROR", "response is unsuccessful");
            } else {
                byte[] inputStream = response.body().bytes();
                movie.setPosterBitmap(BitmapFactory.decodeByteArray(inputStream, 0, inputStream.length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }


    private void handleError(IOException e){
        if (e instanceof JsonMappingException) {
            Log.e("ERROR", "JSON Mapping Problem");
        } else {
            Log.e("ERROR", e.getLocalizedMessage());
        }
    }
}
