/*
 * Developed by arieftb on 6/25/19 3:49 PM.
 * Last Modified 6/23/19 10:46 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.tvshow;

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
import com.arieftb.tonton.model.TvShow;
import com.arieftb.tonton.utils.OnItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<TvShow> tvShows = new ArrayList<>();
    private final Activity activity;

    TvShowsAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<TvShow> getTvShows() {
        return tvShows;
    }

    void setTvShows(List<TvShow> tvShows) {
        if (tvShows == null) return;
        this.tvShows.clear();
        this.tvShows.addAll(tvShows);
    }

    void addItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tv_shows, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        final TvShow TVSHOW = getTvShows().get(position);

        holder.textTvShowTitle.setText(TVSHOW.getTitle());
        holder.textTvShowData.setText(activity.getString(R.string.text_tv_show_data, TVSHOW.getGenre(), TVSHOW.getReleaseDate()));
        holder.textTvShowRating.setText(String.valueOf(TVSHOW.getRating()));
        Glide.with(holder.itemView)
                .load(TVSHOW.getPoster())
                .into(holder.imageTvShowPoster);
    }

    @Override
    public int getItemCount() {
        return getTvShows().size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView textTvShowTitle;
        final TextView textTvShowData;
        final  TextView textTvShowRating;
        final ImageView imageTvShowPoster;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            textTvShowTitle = itemView.findViewById(R.id.text_tv_show_title);
            textTvShowData = itemView.findViewById(R.id.text_tv_show_data);
            textTvShowRating = itemView.findViewById(R.id.text_tv_show_rate);
            imageTvShowPoster = itemView.findViewById(R.id.image_tv_show_poster);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Object object = getTvShows().get(getAdapterPosition());
            onItemClickListener.onItemClick(v, object);
        }
    }
}
