package com.master.movie.moviemaster.internal;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by stefan.bacevic on 6/11/2017.
 */
@Singleton
@Component(modules={AppModule.class, NetworkModule.class})
public interface NetworkComponent {
}
