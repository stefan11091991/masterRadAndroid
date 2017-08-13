package com.master.movie.moviemaster.moviedetails;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.databinding.ActivityMovieDetailsBinding;
import com.master.movie.moviemaster.util.Constants;

/**
 * Created by stefan.bacevic on 7/22/2017.
 */

public class MovieDetailsActivity extends Activity {

    private MovieDetailsViewModel viewModel;
    private ActivityMovieDetailsBinding binding;
    private int movieId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, 0);

        if (viewModel == null) {
            viewModel = new MovieDetailsViewModel(movieId, getApplicationContext());
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        binding.setMovieDetailsViewModel(viewModel);
        viewModel.loadMovieDetails();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.finishSubscriptions();
    }
}
