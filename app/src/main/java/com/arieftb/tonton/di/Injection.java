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

public class Injection {
    public static MovieRepository provideMovieRepo(Application application) {
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new NetworkClient(), application);

        return MovieRepository.getInstance(remoteRepository);
    }
}
