package com.master.movie.moviemaster.internal;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.master.movie.moviemaster.database.DBHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stefan.bacevic on 7/31/2017.
 */

@Module
public class DBModule {

    private Application context;

    public DBModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper() {
        return new DBHelper(context);
    }
}
