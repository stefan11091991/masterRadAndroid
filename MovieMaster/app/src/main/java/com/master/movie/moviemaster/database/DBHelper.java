package com.master.movie.moviemaster.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.master.movie.moviemaster.dto.MovieDetails;
import com.master.movie.moviemaster.internal.MovieMaster;

/**
 * Created by stefan.bacevic on 7/31/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DATABASE_NAME";
    public static final int DATABASE_VERSION = 1;
    public static final String SQL_CREATE_FAVOURITES_TABLE = "CREATE TABLE "
            + DBContract.FavouritesEntry.TABLE_NAME + " (" + DBContract.FavouritesEntry._ID
            + " INTEGER PRIMARY KEY, " + DBContract.FavouritesEntry.MOVIE_ID + " INTEGER, "
            + DBContract.FavouritesEntry.NAME + " TEXT, "
            + DBContract.FavouritesEntry.YEAR + " INTEGER, "
            + DBContract.FavouritesEntry.RATING + " REAL, "
            + DBContract.FavouritesEntry.POSTER + " BLOB, "
            + DBContract.FavouritesEntry.STORYLINE + " TEXT, "
            + DBContract.FavouritesEntry.CAST + " TEXT);";
    public static final String SQL_CREATE_WATCHLIST_TABLE = "CREATE TABLE "
            + DBContract.WatchlistEntry.TABLE_NAME + " (" + DBContract.WatchlistEntry._ID
            + " INTEGER PRIMARY KEY, " + DBContract.WatchlistEntry.MOVIE_ID + " INTEGER, "
            + DBContract.WatchlistEntry.NAME + " TEXT, "
            + DBContract.WatchlistEntry.YEAR + " INTEGER, "
            + DBContract.WatchlistEntry.RATING + " REAL, "
            + DBContract.WatchlistEntry.POSTER + " BLOB, "
            + DBContract.WatchlistEntry.STORYLINE + " TEXT, "
            + DBContract.WatchlistEntry.CAST + " TEXT);";
    public static final String SQL_CREATE_MOVIE_RATING_TABLE = "CREATE TABLE "
            + DBContract.MovieRating.TABLE_NAME + " (" + DBContract.MovieRating.MOVIE_ID
            + " INTEGER PRIMARY KEY, " + DBContract.MovieRating.RATING + " REAL);";
    private static final String SQL_RETRIEVE_RATING = "SELECT " + DBContract.MovieRating.RATING
            + " FROM " + DBContract.MovieRating.TABLE_NAME + " WHERE "
            + DBContract.MovieRating.MOVIE_ID + "=?";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ((MovieMaster) context.getApplicationContext()).getMovieDetailsComponent().inject(this);
    }

    public void insertMovieToFavourites(MovieDetails movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();

        ContentValues values = new ContentValues();
        values.put(DBContract.FavouritesEntry.MOVIE_ID, movie.getId());
        values.put(DBContract.FavouritesEntry.NAME, movie.getName());
        values.put(DBContract.FavouritesEntry.YEAR, movie.getYear());
        values.put(DBContract.FavouritesEntry.RATING, movie.getRating());
        values.put(DBContract.FavouritesEntry.POSTER, DbBitmapUtil.getBytes(movie.getPosterBitmap()));
        values.put(DBContract.FavouritesEntry.STORYLINE, movie.getStoryLine());
        values.put(DBContract.FavouritesEntry.CAST, gson.toJson(movie.getCast()));

        db.insert(DBContract.FavouritesEntry.TABLE_NAME, null, values);
    }

    public void insertMovieToWatchlist(MovieDetails movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();

        ContentValues values = new ContentValues();
        values.put(DBContract.WatchlistEntry.MOVIE_ID, movie.getId());
        values.put(DBContract.WatchlistEntry.NAME, movie.getName());
        values.put(DBContract.WatchlistEntry.YEAR, movie.getYear());
        values.put(DBContract.WatchlistEntry.RATING, movie.getRating());
        values.put(DBContract.WatchlistEntry.POSTER, DbBitmapUtil.getBytes(movie.getPosterBitmap()));
        values.put(DBContract.WatchlistEntry.STORYLINE, movie.getStoryLine());
        values.put(DBContract.WatchlistEntry.CAST, gson.toJson(movie.getCast()));

        db.insert(DBContract.WatchlistEntry.TABLE_NAME, null, values);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FAVOURITES_TABLE);
        db.execSQL(SQL_CREATE_WATCHLIST_TABLE);
        db.execSQL(SQL_CREATE_MOVIE_RATING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.FavouritesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.WatchlistEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.MovieRating.TABLE_NAME);
    }

    public void rateMovie(int movieId, float newRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBContract.MovieRating.MOVIE_ID, movieId);
        values.put(DBContract.MovieRating.RATING, newRating);
        db.insertWithOnConflict(DBContract.MovieRating.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public float getMyMovieRating(int movieId) {
        SQLiteDatabase db = this.getReadableDatabase();
        float rating = 0;
        Cursor cursor = db.rawQuery(SQL_RETRIEVE_RATING, new String[]{String.valueOf(movieId)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            rating = Float.parseFloat(cursor.getString(0));
        }
        cursor.close();
        return rating;
    }
}
