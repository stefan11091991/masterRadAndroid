package com.master.movie.moviemaster.internal;

import android.util.Log;

import java.io.Console;
import java.util.ConcurrentModificationException;

/**
 * Created by stefan.bacevic on 6/11/2017.
 */

public class ApiServiceWrapper {
    private ApiService apiService;

    public ApiServiceWrapper(ApiService apiService) {
        this.apiService = apiService;
    }

    public void dummyMethod(){
        Log.d("MyDebug", "MyDebug apiServiceWrapper");
    }
}
