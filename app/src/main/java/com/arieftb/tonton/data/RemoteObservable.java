package com.arieftb.tonton.data;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.model.response.movies.MoviesResponse;
import com.arieftb.tonton.network.NetworkClient;

import java.util.List;

import io.reactivex.Observable;

public class RemoteObservable {
    public static Observable<List<MovieEntity>> getMovieEntity(NetworkClient networkClient) {
        return networkClient.getApiService().getMovies(BuildConfig.API_KEY)
                .flatMapIterable(MoviesResponse::getResults)
                .map(it -> new MovieEntity(it.getId(), it.getTitle(), it.getOriginalLanguage(), it.getVoteAverage(), it.getPosterPath(), it.getReleaseDate()))
                .toList()
                .toObservable();
    }
}
