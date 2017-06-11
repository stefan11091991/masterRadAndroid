package com.master.movie.moviemaster.internal;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by stefan.bacevic on 6/11/2017.
 */

@Module
public class NetworkModule {

    Retrofit provideRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
//                    TODO add this later
//                    .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.13:8081")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ApiServiceWrapper provideApiServiceWrapper(){
        Retrofit retrofit = provideRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        return new ApiServiceWrapper(apiService);
    }


}

