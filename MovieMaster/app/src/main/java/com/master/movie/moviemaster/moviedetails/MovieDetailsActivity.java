package com.master.movie.moviemaster.moviedetails;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.data.MovieDetailsModel;
import com.master.movie.moviemaster.dto.MovieDetails;
import com.master.movie.moviemaster.internal.MovieMaster;
import com.master.movie.moviemaster.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by stefan.bacevic on 7/22/2017.
 */

public class MovieDetailsActivity extends Activity {

    @BindView(R.id.movieId)
    TextView movieIdTextView;

    //TODO ovo ces da sklonis
    @Inject
    MovieDetailsModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieMaster) getApplication()).getMovieDetailsComponent().inject(this);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        int movieId = getIntent().getIntExtra(Constants.MOVIE_ID, 0);
        model.getMovieDetails(movieId)
                .subscribe(new Subscriber<MovieDetails>() {
                    @Override
                    public void onCompleted() {
                        Log.i("INFO", "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MovieDetails movieDetails) {
                        movieIdTextView.setText(movieDetails.getCast().get(0));
                    }
                });
    }
}
