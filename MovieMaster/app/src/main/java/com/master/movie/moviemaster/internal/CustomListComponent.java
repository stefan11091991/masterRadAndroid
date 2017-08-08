package com.master.movie.moviemaster.internal;

import com.master.movie.moviemaster.customlists.CustomListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by stefan.bacevic on 8/5/2017.
 */
@Singleton
@Component(modules={DBModule.class})
public interface CustomListComponent {
    void inject(CustomListActivity activity);
}
