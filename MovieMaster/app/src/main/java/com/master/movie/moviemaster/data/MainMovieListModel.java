package com.master.movie.moviemaster.data;

import android.util.Log;

import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.internal.ApiServiceWrapper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by stefan on 6/4/2017.
 */

public class MainMovieListModel {
    ApiServiceWrapper apiServiceWrapper;
    List<Movie> cachedMovies;

    public MainMovieListModel(ApiServiceWrapper apiServiceWrapper) {
        this.apiServiceWrapper = apiServiceWrapper;
    }


    public Observable<List<Movie>> loadMovies() {
        if (cachedMovies != null) {
            Log.d("MyDebug", "ucitavam kes");
            return Observable.create((Observable.OnSubscribe<List<Movie>>) this::getMoviesFromCache)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        } else {
            return Observable.create((Observable.OnSubscribe<List<Movie>>) this::getMovies)
                    .flatMap(Observable::from)
                    .map(movie -> apiServiceWrapper.getPoster(movie))
                    .toList()
                    .doOnNext(movies -> cacheMovies(movies))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        }
    }

    private void cacheMovies(List<Movie> movies) {
        cachedMovies = movies;
    }

    private void getMoviesFromCache(Subscriber<? super List<Movie>> subscriber) {
        try {
            subscriber.onNext(cachedMovies);
            subscriber.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
            subscriber.onError(e);
        }
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
