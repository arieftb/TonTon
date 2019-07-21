package com.arieftb.tonton.repo.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.repo.callback.ConnectionCallback;
import com.arieftb.tonton.repo.callback.MovieCallback;
import com.arieftb.tonton.repo.callback.MoviesCallback;
import com.arieftb.tonton.repo.remote.RemoteRepository;
import com.arieftb.tonton.utils.DataDummy;
import com.arieftb.tonton.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MovieRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private final List<MovieEntity> movies = DataDummy.generateMovie();
    private final MovieEntity movie = DataDummy.getMovie(429617);
    private final MovieRepository movieRepository = new MovieRepository(remoteRepository);

    @Test
    public void getMovies() {
        doAnswer(invocation -> {
                    ((MoviesCallback) invocation.getArguments()[0]).onMoviesReceived(movies);
                    return null;
                }).when(remoteRepository).getMovies(any(MoviesCallback.class), any(ConnectionCallback.class));

                movieRepository.getMovies();
                List<MovieEntity> movieEntities = LiveDataTestUtil.getValue(movieRepository.onMoviesReceived());

                verify(remoteRepository, times(1)).getMovies(any(MoviesCallback.class), any(ConnectionCallback.class));

                assertEquals(movies.size(), Objects.requireNonNull(movieEntities).size());
    }

    @Test
    public void getMovieDetail() {
        doAnswer(invocation -> {
            ((MovieCallback) invocation.getArguments()[1]).onMovieReceived(movie);
            return null;
        }).when(remoteRepository).getMovie(anyInt(), any(MovieCallback.class), any(ConnectionCallback.class));

        movieRepository.getMovieDetail(429617);
        MovieEntity movieEntity = LiveDataTestUtil.getValue(movieRepository.onMovieReceived());

        verify(remoteRepository, times(1)).getMovie(anyInt(), any(MovieCallback.class), any(ConnectionCallback.class));

        assertEquals(movie, movieEntity);
    }
}