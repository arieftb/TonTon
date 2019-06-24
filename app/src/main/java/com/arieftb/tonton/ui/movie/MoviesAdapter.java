/*
 * Developed by arieftb on 6/23/19 10:26 PM.
 * Last Modified 6/23/19 10:26 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.movie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private final Activity activity;

    MoviesAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<Movie> getMovies() {
        return movies;
    }

    void setMovies(List<Movie> movies) {
        if (movies == null) return;
        this.movies.clear();
        this.movies.addAll(movies);
    }


    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movies, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        final Movie MOVIE = getMovies().get(position);

        holder.textMovieTitle.setText(MOVIE.getTitle());
        holder.textMovieData.setText(activity.getString(R.string.text_movie_data, MOVIE.getGenre(), MOVIE.getReleaseDate()));
        holder.textMovieRating.setText(String.valueOf(MOVIE.getRating()));
        Glide.with(holder.itemView)
                .load(MOVIE.getPoster())
                .into(holder.imageMoviePoster);
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {

        final TextView textMovieTitle;
        final TextView textMovieData;
        final  TextView textMovieRating;
        final ImageView imageMoviePoster;

        MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            textMovieTitle = itemView.findViewById(R.id.text_movie_title);
            textMovieData = itemView.findViewById(R.id.text_movie_data);
            textMovieRating = itemView.findViewById(R.id.text_movie_rate);
            imageMoviePoster = itemView.findViewById(R.id.image_movie_poster);
        }
    }
}