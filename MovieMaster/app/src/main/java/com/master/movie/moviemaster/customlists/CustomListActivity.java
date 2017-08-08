package com.master.movie.moviemaster.customlists;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.database.DBHelper;
import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.dto.MovieDetails;
import com.master.movie.moviemaster.internal.MovieMaster;
import com.master.movie.moviemaster.mainmovielist.MainMovieListAdapter;
import com.master.movie.moviemaster.util.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by stefan.bacevic on 8/5/2017.
 */

public class CustomListActivity extends Activity {
    private String type;
    @Inject
    DBHelper dbHelper;

    @BindView(R.id.movie_list)
    RecyclerView movieList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private CustomListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieMaster) getApplication()).getCustomListComponent().inject(this);
        type = getIntent().getStringExtra(Constants.TYPE);
        setContentView(R.layout.activity_custom_list);
        ButterKnife.bind(this);
        Log.d("MyDebug", "type is " + type);
        Log.d("MyDebug", "dbhelper " + dbHelper.getAllMoviesFromWatchlist().get(0).getName());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        movieList.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(GONE);
        showMovies(dbHelper.getAllMoviesFromWatchlist());
    }

    public void showMovies(ArrayList<MovieDetails> movies) {
        if (adapter == null) {
            adapter = new CustomListAdapter(movies, this);
            movieList.setAdapter(adapter);
        } else {
            adapter.update(movies);
        }
    }

}
