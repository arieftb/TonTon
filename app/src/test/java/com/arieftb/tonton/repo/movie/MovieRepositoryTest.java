package com.arieftb.tonton.repo.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.arieftb.tonton.BuildConfig;
import com.arieftb.tonton.model.response.movie.MoviesResponse;
import com.arieftb.tonton.network.NetworkClient;
import com.arieftb.tonton.network.NetworkEndPoint;
import com.arieftb.tonton.repo.callback.ConnectionCallback;
import com.arieftb.tonton.repo.callback.MoviesCallback;
import com.arieftb.tonton.repo.remote.RemoteRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;
import io.reactivex.Observer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private NetworkClient networkClient = mock(NetworkClient.class);
    private NetworkEndPoint networkEndPoint = mock(NetworkEndPoint.class);

    @Mock
    private Observer<MoviesResponse> moviesResponseObserver;


    @Before
    private void setBaseUrl() {
        networkClient.setBaseUrl(BuildConfig.BASE_URL_MOVIE);
    }

    @Test
    public void getMovies() {
//        doAnswer(invocation -> {
//            ((MoviesCallback) invocation.getArguments()[0]).onMoviesReceived();
//        }).when(remoteRepository).getMovies(any(MoviesCallback.class), any(ConnectionCallback.class));

        when(networkEndPoint.getMovies(BuildConfig.API_KEY)).thenReturn(Observable.just(Respo))
    }

    @Test
    public void getMovieDetail() {
    }
}