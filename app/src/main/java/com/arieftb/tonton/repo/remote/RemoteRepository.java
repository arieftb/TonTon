/*
 * Developed by arieftb on 7/9/19 2:02 PM.
 * Last Modified 7/9/19 2:02 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.repo.remote;

import android.app.Application;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.model.response.movies.MoviesResponse;
import com.arieftb.tonton.network.NetworkClient;
import com.arieftb.tonton.network.NetworkFailed;
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
    private NetworkFailed networkFailed = new NetworkFailed();
    private final Application application;

    public RemoteRepository(NetworkClient networkClient, Application application) {
        this.networkClient = networkClient;
        this.application = application;
    }

    public static RemoteRepository getInstance(NetworkClient networkClient, Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(networkClient, application);
        }
        return INSTANCE;
    }

    public void getMovies(final MoviesCallback moviesCallback, final ConnectionCallback connectionCallback) {
        connectionCallback.onLoading(true);
        networkClient.setBaseUrl(BuildConfig.BASE_URL_MOVIE);
        networkClient.getApiService().getMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        moviesCallback.onMoviesReceived(moviesResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        connectionCallback.onFailed(networkFailed.getUserErrorMessage(e, application));
                        connectionCallback.onLoading(false);
                    }

                    @Override
                    public void onComplete() {
                        connectionCallback.onLoading(false);
                    }
                });
    }
}
