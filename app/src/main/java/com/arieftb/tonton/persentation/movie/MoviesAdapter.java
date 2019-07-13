/*
 * Developed by arieftb on 6/23/19 10:26 PM.
 * Last Modified 6/23/19 10:26 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.movie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.R;
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.utils.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private final List<MovieEntity> movies = new ArrayList<>();
    private final Activity activity;
    private OnItemClickListener onItemClickListener;

    MoviesAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<MovieEntity> getMovies() {
        return movies;
    }

    void setMovies(List<MovieEntity> movies) {
        if (movies == null) return;
        this.movies.clear();
        this.movies.addAll(movies);
    }

    void addItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movies, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        final MovieEntity MOVIE = getMovies().get(position);

        holder.textMovieTitle.setText(MOVIE.getTitle());
        holder.textMovieData.setText(activity.getString(R.string.text_movie_data, MOVIE.getLang(), MOVIE.getReleaseDate()));
        holder.textMovieRating.setText(String.valueOf(MOVIE.getVoteAverage()));
        Glide.with(holder.itemView)
                .load(BuildConfig.BASE_URL_POSTER + MOVIE.getPoster())
                .apply(new RequestOptions().placeholder(R.drawable.img_placeholder))
                .into(holder.imageMoviePoster);
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Object object = movies.get(getAdapterPosition());
            onItemClickListener.onItemClick(v, object);
        }
    }
}
