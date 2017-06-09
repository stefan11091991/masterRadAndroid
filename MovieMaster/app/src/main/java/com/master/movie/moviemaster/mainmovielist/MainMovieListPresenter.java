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
    private MainMovieListContract.View view;

    public MainMovieListPresenter(MainMovieListModel mainMovieListModel) {
        this.mainMovieListModel = mainMovieListModel;
    }

    public void setView(MainMovieListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadMovies() {
        ArrayList<Movie> movies = mainMovieListModel.getMovies();
        view.showMovies(movies);
    }

    @Override
    public void resetView() {
        view = null;
    }
}
