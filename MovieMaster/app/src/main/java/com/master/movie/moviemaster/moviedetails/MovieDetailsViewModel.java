package com.master.movie.moviemaster.moviedetails;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.data.MovieDetailsModel;
import com.master.movie.moviemaster.dto.MovieDetails;
import com.master.movie.moviemaster.internal.MovieMaster;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by stefan.bacevic on 7/23/2017.
 */

public class MovieDetailsViewModel extends BaseObservable {
    private int movieId;
    private Context context;
    private Subscription subscription;
    private MovieDetails movieDetails;
    public final ObservableBoolean dataLoading = new ObservableBoolean(true);

    @Inject
    MovieDetailsModel model;


    public MovieDetailsViewModel(int movieId, Context context) {
        this.movieId = movieId;
        this.context = context;
        ((MovieMaster) context).getMovieDetailsComponent().inject(this);
    }

    public void loadMovieDetails() {
        if(movieDetails==null) {
            subscription = model.getMovieDetails(movieId)
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
                            dataLoading.set(false);
                            Log.d("MyDebug", "called on next");
                            notifyChange();
                        }
                    });
        } else {
            dataLoading.set(false);
        }

    }

    public void finishSubscriptions(){
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    public void addToFavourites(View view){
        model.addToFavourites(movieDetails);
    }

    public void addToWatchlist(View view){
        model.addToWatchlist(movieDetails);
    }

    public void rateMovie(View view, float newRating, boolean fromUser){
        Log.d("MyDebug", "rating is " + newRating);
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
    public String getRating() {
        if (movieDetails != null) {
            return context.getResources().getString(R.string.rating)
                    + String.valueOf(movieDetails.getRating())
                    + "/10";
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
