package com.master.movie.moviemaster.internal;

import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.dto.MovieDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by stefan.bacevic on 6/11/2017.
 */

public interface ApiService {
    @GET
    Call<Movie[]> getAllMovies(@Url String url);
    @GET
    Call<MovieDetails> getMovieDetail(@Url String url);
}
