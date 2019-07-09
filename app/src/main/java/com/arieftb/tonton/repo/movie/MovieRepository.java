/*
 * Developed by arieftb on 7/9/19 3:23 PM.
 * Last Modified 7/9/19 3:23 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.repo.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arieftb.tonton.data.MovieDataSource;
import com.arieftb.tonton.model.response.ResultsItem;
import com.arieftb.tonton.repo.callback.MoviesCallback;
import com.arieftb.tonton.repo.remote.RemoteRepository;

import java.util.List;

public class MovieRepository implements MovieDataSource {

    private volatile static MovieRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;

    public MovieRepository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static MovieRepository getInstance(RemoteRepository remoteRepository) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteRepository);
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public LiveData<List<ResultsItem>> getMovies() {
        final MutableLiveData<List<ResultsItem>> moviesData = new MutableLiveData<>();

        remoteRepository.getMovies(new MoviesCallback() {
            @Override
            public void onMoviesReceived(List<ResultsItem> movies) {
                moviesData.postValue(movies);
            }
        });

        return moviesData;
    }
}
