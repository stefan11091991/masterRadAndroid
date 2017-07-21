package com.master.movie.moviemaster.internal;

import android.app.Application;

/**
 * Created by stefan.bacevic on 6/4/2017.
 */

public class MovieMaster extends Application {
    private MovieComponent movieComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        movieComponent = DaggerMovieComponent.builder()
                .movieModule(new MovieModule())
                .build();

    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }

}
