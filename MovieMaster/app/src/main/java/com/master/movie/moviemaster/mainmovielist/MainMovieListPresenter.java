package com.master.movie.moviemaster.mainmovielist;

import android.util.Log;

import com.master.movie.moviemaster.data.MainMovieListModel;
import com.master.movie.moviemaster.dto.Movie;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by stefan on 6/4/2017.
 */

public class MainMovieListPresenter implements MainMovieListContract.Presenter {
    private MainMovieListModel mainMovieListModel;
    private MainMovieListContract.View view;
    Subscription movieSubscription;

    public MainMovieListPresenter(MainMovieListModel mainMovieListModel) {
        this.mainMovieListModel = mainMovieListModel;
    }

    @Override
    public void setView(MainMovieListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadMovies(String query) {
        movieSubscription = mainMovieListModel.loadMovies(query)
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("INFO", "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        view.hideProgressBar();
                        view.showMovies((ArrayList<Movie>) movies);
                    }
                });
    }

    @Override
    public void resetView() {
        view = null;
    }

}
