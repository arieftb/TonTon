package com.arieftb.tonton.repo.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arieftb.tonton.data.DataSource;
import com.arieftb.tonton.data.TvShowDataSource;
import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.repo.callback.ConnectionCallback;
import com.arieftb.tonton.repo.remote.RemoteRepository;

import java.util.List;

public class TvShowRepository implements TvShowDataSource, DataSource {

    private volatile static TvShowRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;
    private final MutableLiveData<Boolean> isLoadingData = new MutableLiveData<>();
    private final MutableLiveData<String> errorData = new MutableLiveData<>();
    private final MutableLiveData<List<TvShowEntity>> tvShowItems = new MutableLiveData<>();
    private final MutableLiveData<TvShowEntity> tvShow = new MutableLiveData<>();

    TvShowRepository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static TvShowRepository getInstance(RemoteRepository remoteRepository) {
        if (INSTANCE == null) {
            synchronized (TvShowRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvShowRepository(remoteRepository);
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
    public void getTvShows() {
        remoteRepository.getTvShows(tvShowItems::postValue, new ConnectionCallback() {
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
    public void getTvShow(int id) {
        remoteRepository.getTvShow(id, tvShow::postValue, new ConnectionCallback() {
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
    public LiveData<List<TvShowEntity>> onTvShowsReceived() {
        return tvShowItems;
    }

    @Override
    public LiveData<TvShowEntity> onTvShowReceived() {
        return tvShow;
    }
}
