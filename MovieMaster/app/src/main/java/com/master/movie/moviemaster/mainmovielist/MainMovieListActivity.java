package com.master.movie.moviemaster.mainmovielist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.internal.MovieMaster;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stefan on 6/4/2017.
 */

public class MainMovieListActivity extends Activity implements MainMovieListContract.View {
    @Inject
    MainMovieListPresenter presenter;
    MainMovieListAdapter adapter;

    @BindView(R.id.movie_list)
    RecyclerView movieList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieMaster) getApplication()).getMovieComponent().inject(this);
        setContentView(R.layout.activity_main_movie_list);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        movieList.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        progressBar.setVisibility(View.VISIBLE);
        presenter.loadMovies();
    }

    @Override
     public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.resetView();
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        if (adapter == null) {
            adapter = new MainMovieListAdapter(movies);
            movieList.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }
}
