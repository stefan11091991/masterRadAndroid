package com.master.movie.moviemaster.internal;

import com.master.movie.moviemaster.data.MovieDetailsModel;
import com.master.movie.moviemaster.database.DBHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stefan.bacevic on 7/22/2017.
 */
@Module
public class MovieDetailsModule {
    @Provides
    @Singleton
    MovieDetailsModel provideMovieDetailsModel(ApiServiceWrapper apiServiceWrapper, DBHelper dbHelper) {
        return new MovieDetailsModel(apiServiceWrapper, dbHelper);
    }
}
