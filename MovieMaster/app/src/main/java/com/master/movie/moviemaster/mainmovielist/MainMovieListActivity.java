package com.master.movie.moviemaster.mainmovielist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.internal.MovieMaster;

import javax.inject.Inject;

/**
 * Created by stefan on 6/4/2017.
 */

public class MainMovieListActivity extends Activity implements MainMovieListContract.View {
    @Inject
    MainMovieListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieMaster) getApplication()).getMovieComponent().inject(this);
        Log.d("MyDebug", "Mydebug");
        setContentView(R.layout.activity_main_movie_list);
        presenter.dummyMethod();
    }

    @Override
    public void showMovies() {

    }
}
