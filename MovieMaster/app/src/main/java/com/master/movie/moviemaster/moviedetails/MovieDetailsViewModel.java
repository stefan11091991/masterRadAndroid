package com.master.movie.moviemaster.moviedetails;

import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;

import com.master.movie.moviemaster.data.MovieDetailsModel;
import com.master.movie.moviemaster.internal.MovieMaster;

import javax.inject.Inject;

/**
 * Created by stefan.bacevic on 7/23/2017.
 */

public class MovieDetailsViewModel extends BaseObservable {
    private int movieId;
    private Context context;

    @Inject
    MovieDetailsModel model;


    public MovieDetailsViewModel(int movieId, Context context) {
        this.movieId = movieId;
        ((MovieMaster) context).getMovieDetailsComponent().inject(this);
    }
}
