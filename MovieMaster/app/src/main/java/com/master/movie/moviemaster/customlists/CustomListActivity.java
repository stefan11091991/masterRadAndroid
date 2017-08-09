package com.master.movie.moviemaster.customlists;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.database.DBHelper;
import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.dto.MovieDetails;
import com.master.movie.moviemaster.internal.MovieMaster;
import com.master.movie.moviemaster.mainmovielist.MainMovieListAdapter;
import com.master.movie.moviemaster.moviedetails.MovieDetailsActivity;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        movieList.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(GONE);
        if (type.equals(Constants.WATCHLIST)) {
            showMovies(dbHelper.getAllMoviesFromWatchlist());
        } else {
            showMovies(dbHelper.getAllMoviesFromFavourites());
        }
    }

    public void showMovies(ArrayList<MovieDetails> movies) {
        if (movies == null || movies.isEmpty()) {
            Toast.makeText(this, type.equals(Constants.FAVOURITES) ?
                            getString(R.string.empty_favourites) : getString(R.string.empty_watchlist),
                    Toast.LENGTH_SHORT).show();
        } else {
            if (adapter == null) {
                adapter = new CustomListAdapter(movies, this);
                movieList.setAdapter(adapter);
            } else {
                adapter.update(movies);
            }
        }
    }

    public void gotoMovieDetails(int movieId) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Constants.MOVIE_ID, movieId);
        startActivity(intent);
    }

    public void removeFromList(int movieId) {
        if (type.equals(Constants.WATCHLIST)) {
            dbHelper.removeFromWatchlist(movieId);
            adapter.update(dbHelper.getAllMoviesFromWatchlist());

        } else {
            dbHelper.removeFromFavourites(movieId);
            adapter.update(dbHelper.getAllMoviesFromFavourites());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter = null;
    }
}
