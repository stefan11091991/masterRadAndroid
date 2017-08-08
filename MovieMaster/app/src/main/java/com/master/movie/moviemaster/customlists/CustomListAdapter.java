package com.master.movie.moviemaster.customlists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master.movie.moviemaster.R;
import com.master.movie.moviemaster.dto.Movie;
import com.master.movie.moviemaster.dto.MovieDetails;
import com.master.movie.moviemaster.mainmovielist.*;

import java.util.ArrayList;

/**
 * Created by stefan.bacevic on 8/8/2017.
 */

public class CustomListAdapter extends RecyclerView.Adapter<CustomListMovieHolder> {

    private ArrayList<MovieDetails> moviesDetails;
    Context context;

    public CustomListAdapter(ArrayList<MovieDetails> moviesDetails, Context context) {
        this.moviesDetails = moviesDetails;
        this.context = context;
    }

    @Override
    public CustomListMovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_movie_list, parent, false);
        return new CustomListMovieHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomListMovieHolder holder, int position) {
        MovieDetails movie = moviesDetails.get(position);
        holder.movieName.setText(movie.getName());
        holder.rating.setText(String.valueOf(movie.getRating()));
        holder.year.setText(String.valueOf(movie.getYear()));
        if(movie.getPosterBitmap() == null){

        }
        holder.poster.setImageBitmap(movie.getPosterBitmap());
//        holder.itemView.setOnClickListener(v -> {
//            callback.gotoMovieDetails(movie.getId());
//        });

    }

    @Override
    public int getItemCount() {
        return moviesDetails.size();
    }

    public void update(ArrayList<MovieDetails> movies) {
        this.moviesDetails = movies;
        notifyDataSetChanged();
    }
}
