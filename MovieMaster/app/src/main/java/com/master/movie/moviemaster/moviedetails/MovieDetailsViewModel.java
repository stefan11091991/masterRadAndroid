package com.master.movie.moviemaster.moviedetails;

import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.master.movie.moviemaster.data.MovieDetailsModel;
import com.master.movie.moviemaster.dto.MovieDetails;
import com.master.movie.moviemaster.internal.MovieMaster;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by stefan.bacevic on 7/23/2017.
 */

public class MovieDetailsViewModel extends BaseObservable {
    private int movieId;
    private Context context;
    private MovieDetails movieDetails;

    @Inject
    MovieDetailsModel model;


    public MovieDetailsViewModel(int movieId, Context context) {
        this.movieId = movieId;
        ((MovieMaster) context).getMovieDetailsComponent().inject(this);
    }

    public void loadMovieDetails(){
        model.getMovieDetails(movieId)
                .subscribe(new Subscriber<MovieDetails>() {
                    @Override
                    public void onCompleted() {
                        Log.i("INFO", "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MovieDetails newMovieDetails) {
                        movieDetails = newMovieDetails;
                        Log.d("MyDebug", "called on next");
                    }
                });

    }

    @Bindable
    public String getFirstActor(){
        return movieDetails.getCast().get(0);
    }

    @Bindable
    public String getStoryLine(){
        Log.d("MyDebug", "called getStoryLine while storyline was " + movieDetails.getStoryLine());
        return movieDetails.getStoryLine();
    }

}
