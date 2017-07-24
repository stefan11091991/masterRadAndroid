package com.master.movie.moviemaster.moviedetails;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
        this.context = context;
        ((MovieMaster) context).getMovieDetailsComponent().inject(this);
    }

    public void loadMovieDetails() {
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
                        notifyChange();
                    }
                });

    }

    @Bindable
    public String getFirstActor() {
        return movieDetails.getCast().get(0);
    }

    @Bindable
    public String getStoryLine() {
        if (movieDetails != null) {
            return movieDetails.getStoryLine();
        }
        return "";
    }

    @Bindable
    public String getYear() {
        if (movieDetails != null) {
            return String.valueOf(movieDetails.getYear());
        }
        return "";
    }

    @Bindable
    public String getName() {
        if (movieDetails != null) {
            return movieDetails.getName();
        }
        return "";
    }

    @Bindable
    public Drawable getDrawable() {
        if(movieDetails!=null){
            return new BitmapDrawable(context.getResources(), movieDetails.getPosterBitmap());
        }
        return null;
    }

}
