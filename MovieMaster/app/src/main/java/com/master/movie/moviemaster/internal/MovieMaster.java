package com.master.movie.moviemaster.internal;

import android.app.Application;

/**
 * Created by stefan.bacevic on 6/4/2017.
 */

public class MovieMaster extends Application {
    private MovieComponent movieComponent;
    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent=DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();

        movieComponent = DaggerMovieComponent.builder()
                .appModule(new AppModule(this))
                .movieModule(new MovieModule())
                .build();

    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
