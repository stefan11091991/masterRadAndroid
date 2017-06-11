package com.master.movie.moviemaster.internal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by stefan.bacevic on 6/11/2017.
 */

public interface ApiService {
    @GET
    Call<ResponseBody> getAllMovies(@Url String url);
}
