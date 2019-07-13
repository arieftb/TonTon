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
    public static Observable<List<MovieEntity>> getMoviesMap(NetworkClient networkClient) {
        return networkClient.getApiService().getMovies(BuildConfig.API_KEY)
                .flatMapIterable(MoviesResponse::getResults)
                .map(movieItem -> {
                  MovieEntity movieEntity = new MovieEntity();
                  movieEntity.setId(movieItem.getId());
                  movieEntity.setTitle(movieItem.getTitle());
                  movieEntity.setLang(movieItem.getOriginalLanguage());
                  movieEntity.setReleaseDate(movieItem.getReleaseDate());
                  movieEntity.setPoster(movieItem.getPosterPath());
                  movieEntity.setVoteAverage(movieItem.getVoteAverage());
                  return movieEntity;
                })
                .toList()
                .toObservable();
    }

    public static Observable<MovieEntity> getMovieMap(NetworkClient networkClient, int id){
        return networkClient.getApiService().getMovie(id, BuildConfig.API_KEY)
                .map(movie -> {
                    MovieEntity movieEntity = new MovieEntity();
                    movieEntity.setId(movie.getId());
                    movieEntity.setTitle(movie.getTitle());
                    movieEntity.setLang(movie.getOriginalLanguage());
                    movieEntity.setReleaseDate(movie.getReleaseDate());
                    movieEntity.setPoster(movie.getPosterPath());
                    movieEntity.setOverview(movie.getOverview());
                    movieEntity.setVoteAverage(movie.getVoteAverage());
                    return  movieEntity;
                });
    }

    public static Observable<List<TvShowEntity>> getTvShowsMap(NetworkClient networkClient) {
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

    public static Observable<TvShowEntity> getTvShowMap(NetworkClient networkClient, int id){
        return networkClient.getApiService().getTvShow(id, BuildConfig.API_KEY)
                .map(tv -> {
                    TvShowEntity tvShowEntity = new TvShowEntity();
                    tvShowEntity.setId(tv.getId());
                    tvShowEntity.setTitle(tv.getName());
                    tvShowEntity.setLang(tv.getOriginalLanguage());
                    tvShowEntity.setReleaseDate(tv.getFirstAirDate());
                    tvShowEntity.setPoster(tv.getPosterPath());
                    tvShowEntity.setOverview(tv.getOverview());
                    tvShowEntity.setVoteAverage(tv.getVoteAverage());
                    return  tvShowEntity;
                });
    }
}
