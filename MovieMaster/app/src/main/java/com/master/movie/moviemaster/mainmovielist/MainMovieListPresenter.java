package com.master.movie.moviemaster.mainmovielist;

import android.util.Log;

import com.master.movie.moviemaster.data.MainMovieListModel;
import com.master.movie.moviemaster.dto.Movie;

import java.util.ArrayList;

/**
 * Created by stefan on 6/4/2017.
 */

public class MainMovieListPresenter implements MainMovieListContract.Presenter {
    private MainMovieListModel mainMovieListModel;

    public MainMovieListPresenter(MainMovieListModel mainMovieListModel) {
        this.mainMovieListModel = mainMovieListModel;
    }

    public void dummyMethod() {
        Log.d("MyDebug", "dummy method in presenter");
        mainMovieListModel.dummyMethod();
    }

    @Override
    public ArrayList<Movie> loadMovies() {
        return mainMovieListModel.getMovies();
    }
}
