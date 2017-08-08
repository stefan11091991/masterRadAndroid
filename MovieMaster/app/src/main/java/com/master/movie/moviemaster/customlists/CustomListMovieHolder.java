package com.master.movie.moviemaster.customlists;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.master.movie.moviemaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stefan.bacevic on 8/8/2017.
 */

public class CustomListMovieHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.name)
    public TextView movieName;
    @BindView(R.id.poster)
    public ImageView poster;
    @BindView(R.id.rating)
    public TextView rating;
    @BindView(R.id.year)
    public TextView year;

    public CustomListMovieHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

