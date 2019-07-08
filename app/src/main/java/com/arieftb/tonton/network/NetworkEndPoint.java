/*
 * Developed by arieftb on 7/8/19 2:30 PM.
 * Last Modified 7/8/19 2:30 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.network;

import com.arieftb.tonton.model.response.MoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkEndPoint {
    @GET("/discover/movie")
    Observable<MoviesResponse> getMovies(@Query("api_key") String token);
}
