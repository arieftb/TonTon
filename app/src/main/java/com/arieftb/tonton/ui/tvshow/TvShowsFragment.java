/*
 * Developed by arieftb on 6/24/19 9:07 PM.
 * Last Modified 6/24/19 9:07 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.tvshow;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.TvShow;
import com.arieftb.tonton.ui.tvshowdetail.TvShowDetailActivity;
import com.arieftb.tonton.utils.OnItemClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements OnItemClickListener {

    private RecyclerView recyclerTvShows;

    public TvShowsFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        return new TvShowsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerTvShows = view.findViewById(R.id.recycler_tv_shows_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            TvShowsViewModel tvShowsViewModel = ViewModelProviders.of(getActivity()).get(TvShowsViewModel.class);
            List<TvShow> tvShows = tvShowsViewModel.getTvShows();

            TvShowsAdapter tvShowsAdapter = new TvShowsAdapter(getActivity());
            tvShowsAdapter.setTvShows(tvShows);
            tvShowsAdapter.addItemClickListener(this);

            recyclerTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerTvShows.setHasFixedSize(true);
            recyclerTvShows.setAdapter(tvShowsAdapter);
        }
    }

    @Override
    public void onItemClick(View view, Object object) {
        TvShow tvShow = (TvShow) object;
        Intent intent = new Intent(getContext(), TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.TV_SHOW_ID, tvShow.getId());
        startActivity(intent);
    }
}
