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

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.R;
import com.arieftb.tonton.model.response.tvshow.TvShowItem;
import com.arieftb.tonton.utils.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder> {

    private OnItemClickListener onItemClickListener;
    private final List<TvShowItem> tvShows = new ArrayList<>();
    private final Activity activity;

    TvShowsAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<TvShowItem> getTvShows() {
        return tvShows;
    }

    void setTvShows(List<TvShowItem> tvShows) {
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
        final TvShowItem TV_SHOW = getTvShows().get(position);

        holder.textTvShowTitle.setText(TV_SHOW.getName());
        holder.textTvShowData.setText(activity.getString(R.string.text_tv_show_data, TV_SHOW.getOriginalLanguage(), TV_SHOW.getFirstAirDate()));
        holder.textTvShowRating.setText(String.valueOf(TV_SHOW.getVoteAverage()));
        Glide.with(holder.itemView)
                .load(BuildConfig.BASE_URL_POSTER + TV_SHOW.getBackdropPath())
                .apply(new RequestOptions().placeholder(R.drawable.img_placeholder))
                .into(holder.imageTvShowPoster);
    }

    @Override
    public int getItemCount() {
        return getTvShows().size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView textTvShowTitle;
        final TextView textTvShowData;
        final TextView textTvShowRating;
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
