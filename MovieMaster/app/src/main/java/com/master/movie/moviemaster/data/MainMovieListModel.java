package com.master.movie.moviemaster.data;

import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.internal.ApiServiceWrapper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by stefa on 6/4/2017.
 */

public class MainMovieListModel {
    ApiServiceWrapper apiServiceWrapper;

    public MainMovieListModel(ApiServiceWrapper apiServiceWrapper) {
        this.apiServiceWrapper = apiServiceWrapper;
    }


    public Observable<List<Movie>> loadMovies() {
        return Observable.create((Observable.OnSubscribe<List<Movie>>) this::getMovies)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private void getMovies(Subscriber<? super List<Movie>> subscriber) {
        try {
            subscriber.onNext(apiServiceWrapper.getAllMovies());
            subscriber.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
    }

}
