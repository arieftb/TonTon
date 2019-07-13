package com.arieftb.tonton.data;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.model.response.movie.MoviesResponse;
import com.arieftb.tonton.model.response.tvshow.TvShowsResponse;
import com.arieftb.tonton.network.NetworkClient;

import java.util.List;

import io.reactivex.Observable;

public class RemoteObservable {
    public static Observable<List<MovieEntity>> getMovieMap(NetworkClient networkClient) {
        return networkClient.getApiService().getMovies(BuildConfig.API_KEY)
                .flatMapIterable(MoviesResponse::getResults)
                .map(movieItem -> {
                  MovieEntity movieEntity = new MovieEntity();
                  movieEntity.setId(movieItem.getId());
                  movieEntity.setTitle(movieItem.getTitle());
                  movieEntity.setLang(movieItem.getOriginalLanguage());
                  movieEntity.setReleaseDate(movieItem.getReleaseDate());
                  movieEntity.setPoster(movieItem.getPosterPath());
                  return movieEntity;
                })
                .toList()
                .toObservable();
    }

    public static Observable<List<TvShowEntity>> getTvShowMap(NetworkClient networkClient) {
        return networkClient.getApiService().getTvShows(BuildConfig.API_KEY)
                .flatMapIterable(TvShowsResponse::getResults)
                .map(tvShowItem -> {
                    TvShowEntity tvShowEntity = new TvShowEntity();
                    tvShowEntity.setId(tvShowItem.getId());
                    tvShowEntity.setTitle(tvShowItem.getOriginalName());
                    tvShowEntity.setLang(tvShowItem.getOriginalLanguage());
                    tvShowEntity.setVoteAverage(tvShowItem.getVoteAverage());
                    tvShowEntity.setPoster(tvShowItem.getPosterPath());
                    tvShowEntity.setReleaseDate(tvShowItem.getFirstAirDate());
                    return tvShowEntity;
                })
                .toList()
                .toObservable();
    }
}
