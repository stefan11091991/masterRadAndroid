package com.master.movie.moviemaster.mainmovielist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.dto.Movie;

import java.util.ArrayList;

/**
 * Created by stefan on 6/4/2017.
 */

public class MainMovieListAdapter extends RecyclerView.Adapter<MovieHolder> {
    private ArrayList<Movie> movies;
    public MainMovieListAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_movie_list, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movieName.setText(movie.getName());
        holder.rating.setText(String.valueOf(movie.getRating()));
        holder.year.setText(String.valueOf(movie.getYear()));
        if(movie.getPosterBitmap() == null){

        }
        holder.poster.setImageBitmap(movie.getPosterBitmap());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
