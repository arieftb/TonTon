/*
 * Developed by arieftb on 7/9/19 2:02 PM.
 * Last Modified 7/9/19 2:02 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.repo.remote;

import android.app.Application;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.data.RemoteObservable;
import com.arieftb.tonton.network.NetworkClient;
import com.arieftb.tonton.network.NetworkFailed;
import com.arieftb.tonton.repo.callback.ConnectionCallback;
import com.arieftb.tonton.repo.callback.MoviesCallback;
import com.arieftb.tonton.repo.callback.TvShowCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private NetworkClient networkClient;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private NetworkFailed networkFailed = new NetworkFailed();
    private final Application application;

    private RemoteRepository(NetworkClient networkClient, Application application) {
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
        compositeDisposable.add(RemoteObservable.getMoviesMap(networkClient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe( d -> connectionCallback.onLoading(true))
                .doOnComplete( () -> connectionCallback.onLoading(false))
                .subscribe(moviesCallback::onMoviesReceived, e -> {
                    connectionCallback.onFailed(networkFailed.getUserErrorMessage(e, application));
                    connectionCallback.onLoading(false);
                })
        );
    }

    public void getTvShows(final TvShowCallback tvShowCallback, final ConnectionCallback connectionCallback) {
        connectionCallback.onLoading(true);
        networkClient.setBaseUrl(BuildConfig.BASE_URL_MOVIE);
        compositeDisposable.add(RemoteObservable.getTvShowsMap(networkClient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe( d -> connectionCallback.onLoading(true))
                .doOnComplete( () -> connectionCallback.onLoading(false))
                .subscribe(tvShowCallback::onTvShowReceived, e-> {
                    connectionCallback.onFailed(networkFailed.getUserErrorMessage(e, application));
                    connectionCallback.onLoading(false);
                })
        );
//        networkClient.getApiService().getTvShows(BuildConfig.API_KEY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<TvShowsResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        compositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(TvShowsResponse tvShowsResponse) {
//                        tvShowCallback.onTvShowReceived(tvShowsResponse.getResults());
//                        connectionCallback.onFailed(null);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        connectionCallback.onFailed(networkFailed.getUserErrorMessage(e, application));
//                        connectionCallback.onLoading(false);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        connectionCallback.onLoading(false);
//                    }
//                });
    }
}

