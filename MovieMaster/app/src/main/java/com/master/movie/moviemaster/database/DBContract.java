package com.master.movie.moviemaster.database;

import android.provider.BaseColumns;

/**
 * Created by stefan.bacevic on 7/31/2017.
 */

public final class DBContract {

    public static class FavouritesEntry implements BaseColumns{
        public static final String TABLE_NAME = "favourites";
        public static final String MOVIE_ID = "movie_id";
        public static final String NAME = "name";
        public static final String YEAR = "year";
        public static final String RATING = "rating";
        public static final String POSTER = "poster";
        public static final String STORYLINE = "storyline";
        public static final String CAST = "cast";
    }

    public static class WatchlistEntry implements BaseColumns{
        public static final String TABLE_NAME = "watchlist";
        public static final String MOVIE_ID = "movie_id";
        public static final String NAME = "name";
        public static final String YEAR = "year";
        public static final String RATING = "rating";
        public static final String POSTER = "poster";
        public static final String STORYLINE = "storyline";
        public static final String CAST = "cast";
    }

    public static class MovieRating implements BaseColumns{
        public static final String TABLE_NAME = "movie_rating";
        public static final String MOVIE_ID = "movie_id";
        public static final String RATING = "rating";

    }

}
