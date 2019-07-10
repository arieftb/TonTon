/*
 * Developed by arieftb on 7/9/19 3:23 PM.
 * Last Modified 7/9/19 3:23 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.repo.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arieftb.tonton.data.DataSource;
import com.arieftb.tonton.data.MovieDataSource;
import com.arieftb.tonton.model.response.movies.Movie;
import com.arieftb.tonton.repo.callback.ConnectionCallback;
import com.arieftb.tonton.repo.remote.RemoteRepository;

import java.util.List;

public class MovieRepository implements MovieDataSource, DataSource {

    private volatile static MovieRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;
    private MutableLiveData<Boolean> isLoadingData = new MutableLiveData<>();
    private MutableLiveData<String> errorData = new MutableLiveData<>();

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
    public LiveData<List<Movie>> getMovies() {
        final MutableLiveData<List<Movie>> moviesData = new MutableLiveData<>();

        remoteRepository.getMovies(moviesData::postValue, new ConnectionCallback() {
            @Override
            public void onLoading(Boolean isLoading) {
                isLoadingData.postValue(isLoading);
            }

            @Override
            public void onFailed(String message) {
                errorData.postValue(message);
            }
        });

        return moviesData;
    }


    @Override
    public LiveData<Boolean> isLoading() {
        return isLoadingData;
    }

    @Override
    public LiveData<String> onError() {
        return errorData;
    }
}
