/*
 * Developed by arieftb on 6/24/19 9:07 PM.
 * Last Modified 6/24/19 9:07 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshow;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.persentation.tvshowdetail.TvShowDetailActivity;
import com.arieftb.tonton.utils.DialogHelper;
import com.arieftb.tonton.utils.OnItemClickListener;
import com.arieftb.tonton.utils.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerTvShows;
    private TvShowsViewModel tvShowsViewModel;
    private SwipeRefreshLayout swipeTvShowsReload;

    public TvShowsFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        return new TvShowsFragment();
    }

    @NonNull
    private static TvShowsViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowsViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            tvShowsViewModel = obtainViewModel(getActivity());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerTvShows = view.findViewById(R.id.recycler_tv_shows_list);
        swipeTvShowsReload = view.findViewById(R.id.swipe_tv_shows_reload);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            onLoading();
            onTvShowsReceived();
            onError();

            swipeTvShowsReload.setOnRefreshListener(this);
            swipeTvShowsReload.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void onLoading() {
        tvShowsViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> swipeTvShowsReload.setRefreshing(isLoading));
    }


    private void onTvShowsReceived() {
        tvShowsViewModel.getTvShows().observe(getViewLifecycleOwner(), tvShowItems -> {
            TvShowsAdapter tvShowsAdapter = new TvShowsAdapter(getActivity());
            tvShowsAdapter.setTvShows(tvShowItems);
            tvShowsAdapter.notifyDataSetChanged();
            tvShowsAdapter.addItemClickListener(this);

            recyclerTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerTvShows.setHasFixedSize(true);
            recyclerTvShows.setAdapter(tvShowsAdapter);
        });
    }

    private void onError() {
        tvShowsViewModel.getMessageError().observe(getViewLifecycleOwner(), message -> {
            if (message != null) {
                new DialogHelper(getActivity())
                        .setMessage(message)
                        .setPrimaryButton(R.string.btn_title_ok, (dialogInterface, i) -> dialogInterface.dismiss())
                        .create().show();
            }
        });
    }

    @Override
    public void onItemClick(View view, Object object) {
        TvShowEntity tvShow = (TvShowEntity) object;
        Intent intent = new Intent(getContext(), TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.TV_SHOW_ID, tvShow.getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        tvShowsViewModel.loadData();
    }
}
