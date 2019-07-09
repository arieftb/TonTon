/*
 * Developed by arieftb on 7/9/19 2:02 PM.
 * Last Modified 7/9/19 2:02 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.repo.remote;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.model.response.movies.MoviesResponse;
import com.arieftb.tonton.network.NetworkClient;
import com.arieftb.tonton.repo.callback.ConnectionCallback;
import com.arieftb.tonton.repo.callback.MoviesCallback;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private NetworkClient networkClient;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RemoteRepository(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public static RemoteRepository getInstance(NetworkClient networkClient) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(networkClient);
        }
        return INSTANCE;
    }

    public void getMovies(final MoviesCallback moviesCallback, final ConnectionCallback connectionCallback) {
        networkClient.setBaseUrl(BuildConfig.BASE_URL_MOVIE);
        networkClient.getApiService().getMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                        connectionCallback.onLoading(true);
                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        moviesCallback.onMoviesReceived(moviesResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        connectionCallback.onLoading(false);
                    }
                });
    }
}
