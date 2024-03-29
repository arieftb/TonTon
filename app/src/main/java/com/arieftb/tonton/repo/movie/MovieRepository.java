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
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.repo.callback.ConnectionCallback;
import com.arieftb.tonton.repo.remote.RemoteRepository;

import java.util.List;

public class MovieRepository implements MovieDataSource, DataSource {

    private volatile static MovieRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;
    private final MutableLiveData<Boolean> isLoadingData = new MutableLiveData<>();
    private final MutableLiveData<String> errorData = new MutableLiveData<>();
    private final MutableLiveData<List<MovieEntity>> moviesData = new MutableLiveData<>();
    private final MutableLiveData<MovieEntity> movieData = new MutableLiveData<>();

    MovieRepository(RemoteRepository remoteRepository) {
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
    public LiveData<Boolean> isLoading() {
        return isLoadingData;
    }

    @Override
    public LiveData<String> onError() {
        return errorData;
    }


    @Override
    public void getMovies() {
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
    }

    @Override
    public void getMovieDetail(int id) {
        remoteRepository.getMovie(id, movieData::postValue, new ConnectionCallback() {
            @Override
            public void onLoading(Boolean isLoading) {
                isLoadingData.postValue(isLoading);
            }

            @Override
            public void onFailed(String message) {
                errorData.postValue(message);
            }
        });
    }

    @Override
    public LiveData<List<MovieEntity>> onMoviesReceived() {
        return moviesData;
    }

    @Override
    public LiveData<MovieEntity> onMovieReceived() {
        return movieData;
    }
}
