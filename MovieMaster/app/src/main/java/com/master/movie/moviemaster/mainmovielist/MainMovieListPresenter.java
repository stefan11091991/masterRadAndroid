package com.master.movie.moviemaster.mainmovielist;

import android.util.Log;

import com.master.movie.moviemaster.data.MainMovieListModel;

/**
 * Created by stefan on 6/4/2017.
 */

public class MainMovieListPresenter {
    private MainMovieListModel mainMovieListModel;

    public MainMovieListPresenter(MainMovieListModel mainMovieListModel) {
        this.mainMovieListModel = mainMovieListModel;
    }

    public void dummyMethod() {
        Log.d("MyDebug", "dummy method in presenter");
        mainMovieListModel.dummyMethod();
    }
}
