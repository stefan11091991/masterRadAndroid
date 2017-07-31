package com.master.movie.moviemaster.database;

import android.content.ContentValues;
import android.content.Context;
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
            +DBContract.FavouritesEntry.TABLE_NAME + " (" + DBContract.FavouritesEntry._ID
            + " INTEGER PRIMARY KEY, " + DBContract.FavouritesEntry.MOVIE_ID + " INTEGER, "
            + DBContract.FavouritesEntry.NAME + " TEXT, "
            + DBContract.FavouritesEntry.YEAR + " INTEGER, "
            + DBContract.FavouritesEntry.RATING + " REAL, "
            + DBContract.FavouritesEntry.POSTER + " BLOB, "
            + DBContract.FavouritesEntry.STORYLINE + " TEXT, "
            + DBContract.FavouritesEntry.CAST + " TEXT);";
    public static final String SQL_CREATE_WATCHLIST_TABLE = "CREATE TABLE "
            +DBContract.WathclistEntry.TABLE_NAME + " (" + DBContract.WathclistEntry._ID
            + " INTEGER PRIMARY KEY, " + DBContract.WathclistEntry.MOVIE_ID + " INTEGER, "
            + DBContract.WathclistEntry.NAME + " TEXT, "
            + DBContract.WathclistEntry.YEAR + " INTEGER, "
            + DBContract.WathclistEntry.RATING + " REAL, "
            + DBContract.WathclistEntry.POSTER + " BLOB, "
            + DBContract.WathclistEntry.STORYLINE + " TEXT, "
            + DBContract.WathclistEntry.CAST + " TEXT);";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ((MovieMaster) context.getApplicationContext()).getMovieDetailsComponent().inject(this);
    }

    public void insertMovieToFavourites(MovieDetails movie){
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

    public void insertMovieToWatchlist(MovieDetails movie){
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();

        ContentValues values = new ContentValues();
        values.put(DBContract.WathclistEntry.MOVIE_ID, movie.getId());
        values.put(DBContract.WathclistEntry.NAME, movie.getName());
        values.put(DBContract.WathclistEntry.YEAR, movie.getYear());
        values.put(DBContract.WathclistEntry.RATING, movie.getRating());
        values.put(DBContract.WathclistEntry.POSTER, DbBitmapUtil.getBytes(movie.getPosterBitmap()));
        values.put(DBContract.WathclistEntry.STORYLINE, movie.getStoryLine());
        values.put(DBContract.WathclistEntry.CAST, gson.toJson(movie.getCast()));

        db.insert(DBContract.WathclistEntry.TABLE_NAME, null, values);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FAVOURITES_TABLE);
        db.execSQL(SQL_CREATE_WATCHLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
