package com.master.movie.moviemaster.data;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.internal.ApiServiceWrapper;
import com.master.movie.moviemaster.util.Constants;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by stefan on 6/4/2017.
 */

public class MainMovieListModel {
    ApiServiceWrapper apiServiceWrapper;

    public MainMovieListModel(ApiServiceWrapper apiServiceWrapper) {
        this.apiServiceWrapper = apiServiceWrapper;
    }


    public Observable<List<Movie>> loadMovies() {
        return Observable.create((Observable.OnSubscribe<List<Movie>>) this::getMovies)
                .flatMap(Observable::from)
                .map(movie -> apiServiceWrapper.getPoster(movie))
                .toList()
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
