/*
 * Developed by arieftb on 6/22/19 10:21 PM.
 * Last Modified 6/22/19 10:21 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.movie;


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
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.persentation.moviedetail.MovieDetailActivity;
import com.arieftb.tonton.utils.DialogHelper;
import com.arieftb.tonton.utils.OnItemClickListener;
import com.arieftb.tonton.utils.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerViewMovies;
    private MoviesViewModel moviesViewModel;
    private SwipeRefreshLayout swipeMoviesReload;

    public MoviesFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        return new MoviesFragment();
    }

    @NonNull
    private static MoviesViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MoviesViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() != null) {
            moviesViewModel = obtainViewModel(getActivity());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewMovies = view.findViewById(R.id.recycler_movies_list);
        swipeMoviesReload = view.findViewById(R.id.swipe_movies_reload);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            onLoading();
            onMoviesReceived();
            onError();

            swipeMoviesReload.setOnRefreshListener(this);
            swipeMoviesReload.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void onLoading() {
        moviesViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> swipeMoviesReload.setRefreshing(isLoading));
    }

    private void onMoviesReceived() {
        MoviesAdapter moviesAdapter = new MoviesAdapter(getActivity());

        moviesViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            moviesAdapter.setMovies(movies);
            moviesAdapter.notifyDataSetChanged();
            moviesAdapter.addItemClickListener(this);

            recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewMovies.setHasFixedSize(true);
            recyclerViewMovies.setAdapter(moviesAdapter);
        });
    }

    private void onError() {
        moviesViewModel.getMessageError().observe(getViewLifecycleOwner(), message -> {
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
        MovieEntity movie = (MovieEntity) object;
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_ID, movie.getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        moviesViewModel.getMoviesData();
    }
}
