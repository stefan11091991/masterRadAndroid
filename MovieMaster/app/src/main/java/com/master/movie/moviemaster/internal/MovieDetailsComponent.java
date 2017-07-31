package com.master.movie.moviemaster.internal;

import com.master.movie.moviemaster.database.DBHelper;
import com.master.movie.moviemaster.mainmovielist.MainMovieListActivity;
import com.master.movie.moviemaster.moviedetails.MovieDetailsActivity;
import com.master.movie.moviemaster.moviedetails.MovieDetailsViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by stefan.bacevic on 7/22/2017.
 */
@Singleton
@Component(modules={MovieDetailsModule.class, NetworkModule.class, DBModule.class})
public interface MovieDetailsComponent {
    void inject(MovieDetailsViewModel viewModel);

    void inject(DBHelper dbHelper);
}
