/*
 * Developed by arieftb on 7/9/19 3:18 PM.
 * Last Modified 7/9/19 3:18 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.di;

import android.app.Application;

import com.arieftb.tonton.network.NetworkClient;
import com.arieftb.tonton.repo.movie.MovieRepository;
import com.arieftb.tonton.repo.remote.RemoteRepository;
import com.arieftb.tonton.repo.tvshow.TvShowRepository;

public class Injection {
    public static MovieRepository provideMovieRepo(Application application) {
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new NetworkClient(), application);
        return MovieRepository.getInstance(remoteRepository);
    }

    public static TvShowRepository provideTvShowRepo(Application application) {
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new NetworkClient(), application);
        return TvShowRepository.getInstance(remoteRepository);
    }
}
