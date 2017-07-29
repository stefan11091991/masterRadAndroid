package com.master.movie.moviemaster.mainmovielist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.internal.MovieMaster;
import com.master.movie.moviemaster.moviedetails.MovieDetailsActivity;
import com.master.movie.moviemaster.util.Constants;
import com.master.movie.moviemaster.util.LayoutUtils;

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
    @BindView(R.id.header_name)
    TextView headerName;
    @BindView(R.id.search)
    View search;
    @BindView(R.id.search_clear)
    View searchClear;
    @BindView(R.id.search_box)
    EditText searchBox;
    @BindView(R.id.navigation_holder)
    ViewGroup navigationHolder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieMaster) getApplication()).getMovieComponent().inject(this);
        setContentView(R.layout.activity_main_movie_list);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        movieList.setLayoutManager(layoutManager);
        headerName.setText(getString(R.string.movie_master));

        setupSearch();
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
    public void gotoMovieDetails(int movieId) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Constants.MOVIE_ID, movieId);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.resetView();
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        if (adapter == null) {
            adapter = new MainMovieListAdapter(movies, this);
            movieList.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    private void setupSearch() {
        search.setVisibility(View.VISIBLE);
        search.setOnClickListener(v -> {
            navigationHolder.getChildAt(0).setVisibility(View.INVISIBLE);
            navigationHolder.getChildAt(1).setVisibility(View.VISIBLE);
            LayoutUtils.showKeyboard(searchBox);
        });
        searchClear.setOnClickListener(v -> {
            navigationHolder.getChildAt(0).setVisibility(View.VISIBLE);
            navigationHolder.getChildAt(1).setVisibility(View.INVISIBLE);
            LayoutUtils.hideKeyboard(searchBox);
            searchBox.setText("");
        });
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString();
//                presenter.queryResults(collectFilterValues(), query);
            }
        });

    }

}
