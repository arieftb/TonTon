/*
 * Developed by arieftb on 6/22/19 10:21 PM.
 * Last Modified 6/22/19 10:21 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.movie;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.response.movies.Movie;
import com.arieftb.tonton.ui.moviedetail.MovieDetailActivity;
import com.arieftb.tonton.utils.OnItemClickListener;
import com.arieftb.tonton.utils.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements OnItemClickListener {

    private RecyclerView recyclerViewMovies;
    private MoviesViewModel moviesViewModel;
    private ProgressBar progressMoviesLoad;

    public MoviesFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        return new MoviesFragment();
    }

    @NonNull
    private static MoviesViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewMovies = view.findViewById(R.id.recycler_movies_list);
        progressMoviesLoad = view.findViewById(R.id.progress_movies_load);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            MoviesAdapter moviesAdapter = new MoviesAdapter(getActivity());

            moviesViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
                moviesAdapter.setMovies(movies);
                moviesAdapter.notifyDataSetChanged();
                moviesAdapter.addItemClickListener(this);
            });

            moviesViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> progressMoviesLoad.setVisibility(isLoading ? View.VISIBLE : View.GONE));


            recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewMovies.setHasFixedSize(true);
            recyclerViewMovies.setAdapter(moviesAdapter);
        }
    }

    @Override
    public void onItemClick(View view, Object object) {
        Movie movie = (Movie) object;
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_ID, movie.getId());
        startActivity(intent);
    }
}
