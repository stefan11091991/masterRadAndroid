package com.master.movie.moviemaster.mainmovielist;

import com.master.movie.moviemaster.dto.Movie;

import java.util.ArrayList;

/**
 * Created by stefan on 6/4/2017.
 */

public interface MainMovieListContract {
    interface Presenter {
        void setView(MainMovieListContract.View view);
        void loadMovies(String query);
        void resetView();
    }

    interface View {
        void showMovies(ArrayList<Movie> movies);
        void hideProgressBar();
        void gotoMovieDetails(int movieId);
    }
}
