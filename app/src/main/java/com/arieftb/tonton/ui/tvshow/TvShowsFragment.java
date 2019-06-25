/*
 * Developed by arieftb on 6/24/19 9:07 PM.
 * Last Modified 6/24/19 9:07 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.tvshow;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.TvShow;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment {

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

            recyclerTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerTvShows.setHasFixedSize(true);
            recyclerTvShows.setAdapter(tvShowsAdapter);
        }
    }
}
