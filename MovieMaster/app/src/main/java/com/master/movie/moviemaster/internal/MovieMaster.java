package com.master.movie.moviemaster.internal;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by stefan.bacevic on 6/4/2017.
 */

public class MovieMaster extends Application {
    private MovieComponent movieComponent;
    private MovieDetailsComponent movieDetailsComponent;
    private CustomListComponent customListComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Stetho.initializeWithDefaults(this);

        movieComponent = DaggerMovieComponent.builder()
                .movieModule(new MovieModule())
                .build();

        movieDetailsComponent = DaggerMovieDetailsComponent.builder()
                .dBModule(new DBModule(this))
                .movieDetailsModule(new MovieDetailsModule())
                .build();

        customListComponent = DaggerCustomListComponent.builder()
                .dBModule(new DBModule(this))
                .build();
    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }

    public MovieDetailsComponent getMovieDetailsComponent() {
        return movieDetailsComponent;
    }

    public CustomListComponent getCustomListComponent() {
        return customListComponent;
    }
}
