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
import com.arieftb.tonton.repo.callback.MovieCallback;
import com.arieftb.tonton.repo.callback.MoviesCallback;
import com.arieftb.tonton.repo.callback.TvShowCallback;
import com.arieftb.tonton.repo.callback.TvShowsCallback;
import com.arieftb.tonton.utils.EspressoIdlingResource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private final NetworkClient networkClient;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final NetworkFailed networkFailed = new NetworkFailed();
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
        EspressoIdlingResource.increment();
        networkClient.setBaseUrl(BuildConfig.BASE_URL_MOVIE);
        compositeDisposable.add(RemoteObservable.getMoviesMap(networkClient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe( d -> connectionCallback.onLoading(true))
                .doOnComplete( () -> {
                    connectionCallback.onLoading(false);
                    EspressoIdlingResource.decrement();
                })
                .subscribe(movieEntities -> {
                    moviesCallback.onMoviesReceived(movieEntities);
                    connectionCallback.onFailed(null);
                }, e -> {
                    connectionCallback.onFailed(networkFailed.getUserErrorMessage(e, application));
                    connectionCallback.onLoading(false);
                })
        );
    }

    public void getTvShows(final TvShowsCallback tvShowsCallback, final ConnectionCallback connectionCallback) {
        connectionCallback.onLoading(true);
        EspressoIdlingResource.increment();
        networkClient.setBaseUrl(BuildConfig.BASE_URL_MOVIE);
        compositeDisposable.add(RemoteObservable.getTvShowsMap(networkClient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe( d -> connectionCallback.onLoading(true))
                .doOnComplete( () -> {
                    connectionCallback.onLoading(false);
                    EspressoIdlingResource.decrement();
                })
                .subscribe(tvShowsCallback::onTvShowReceived, e-> {
                    connectionCallback.onFailed(networkFailed.getUserErrorMessage(e, application));
                    connectionCallback.onLoading(false);
                })
        );
    }

    public void getMovie(int id, final MovieCallback movieCallback, final ConnectionCallback connectionCallback) {
        connectionCallback.onLoading(true);
        EspressoIdlingResource.increment();
        networkClient.setBaseUrl(BuildConfig.BASE_URL_MOVIE);
        compositeDisposable.add(RemoteObservable.getMovieMap(networkClient, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe( d -> connectionCallback.onLoading(true))
                .doOnComplete( () -> {
                    connectionCallback.onLoading(false);
                    EspressoIdlingResource.decrement();
                })
                .subscribe(movieEntity -> {
                    movieCallback.onMovieReceived(movieEntity);
                    connectionCallback.onFailed(null);
                }, e-> {
                    movieCallback.onMovieReceived(null);
                    connectionCallback.onFailed(networkFailed.getUserErrorMessage(e, application));
                    connectionCallback.onLoading(false);
                })
        );
    }

    public void getTvShow(int id, final TvShowCallback tvShowCallback, final ConnectionCallback connectionCallback) {
        connectionCallback.onLoading(true);
        EspressoIdlingResource.increment();
        networkClient.setBaseUrl(BuildConfig.BASE_URL_MOVIE);
        compositeDisposable.add(RemoteObservable.getTvShowMap(networkClient, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe( d -> connectionCallback.onLoading(true))
                .doOnComplete( () -> {
                    connectionCallback.onLoading(false);
                    EspressoIdlingResource.decrement();
                })
                .subscribe(tvShowEntity -> {
                    tvShowCallback.onTvShowReceived(tvShowEntity);
                    connectionCallback.onFailed(null);
                }, e-> {
                    tvShowCallback.onTvShowReceived(null);
                    connectionCallback.onFailed(networkFailed.getUserErrorMessage(e, application));
                    connectionCallback.onLoading(false);
                })
        );
    }
}

