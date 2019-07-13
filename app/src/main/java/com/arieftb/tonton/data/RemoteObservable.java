package com.arieftb.tonton.data;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.model.Movie;
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.model.response.movies.MoviesResponse;
import com.arieftb.tonton.model.response.tvshow.TvShowsResponse;
import com.arieftb.tonton.network.NetworkClient;

import java.util.List;

import io.reactivex.Observable;

public class RemoteObservable {
    public static Observable<List<MovieEntity>> getMovieMap(NetworkClient networkClient) {
        return networkClient.getApiService().getMovies(BuildConfig.API_KEY)
                .flatMapIterable(MoviesResponse::getResults)
                .map(it -> new MovieEntity(it.getId(), it.getTitle(), it.getOriginalLanguage(), it.getVoteAverage(), it.getPosterPath(), it.getReleaseDate()))
                .toList()
                .toObservable();
    }

    public static Observable<List<TvShowEntity>> getTvShowMap(NetworkClient networkClient) {
        return networkClient.getApiService().getTvShows(BuildConfig.API_KEY)
                .flatMapIterable(TvShowsResponse::getResults)
                .map(it -> new TvShowEntity(it.getId(), it.getName(), it.getOriginalLanguage(), it.getVoteAverage(), it.getPosterPath(), it.getFirstAirDate()))
                .toList()
                .toObservable();
    }
}
