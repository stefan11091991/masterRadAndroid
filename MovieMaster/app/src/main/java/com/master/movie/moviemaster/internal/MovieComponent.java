package com.master.movie.moviemaster.internal;

import com.master.movie.moviemaster.mainmovielist.MainMovieListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by stefan on 6/4/2017.
 */
@Singleton
@Component(modules={MovieModule.class, NetworkModule.class})
public interface MovieComponent {
void inject(MainMovieListActivity activity);
}
