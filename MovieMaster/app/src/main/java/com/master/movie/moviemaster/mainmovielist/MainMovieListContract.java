package com.master.movie.moviemaster.mainmovielist;

import com.master.movie.moviemaster.dto.Movie;

import java.util.ArrayList;

/**
 * Created by stefan on 6/4/2017.
 */

public interface MainMovieListContract {
    public interface Presenter{
        ArrayList<Movie> loadMovies();
    }

    public interface View {
        void showMovies();
    }
}
