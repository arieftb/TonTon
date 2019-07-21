package com.arieftb.tonton.repo.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.arieftb.tonton.model.entity.TvShowEntity;
import com.arieftb.tonton.repo.callback.ConnectionCallback;
import com.arieftb.tonton.repo.callback.TvShowsCallback;
import com.arieftb.tonton.repo.remote.RemoteRepository;
import com.arieftb.tonton.utils.DataDummy;
import com.arieftb.tonton.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TvShowRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private List<TvShowEntity> tvShows = DataDummy.generateTvShows();
    private TvShowRepository tvShowRepository = new TvShowRepository(remoteRepository);

    @Test
    public void getTvShows() {
        doAnswer(invocation -> {
            ((TvShowsCallback) invocation.getArguments()[0]).onTvShowReceived(tvShows);
            return null;
        }).when(remoteRepository).getTvShows(any(TvShowsCallback.class), any(ConnectionCallback.class));

        tvShowRepository.getTvShows();
        List<TvShowEntity> tvShowEntities = LiveDataTestUtil.getValue(tvShowRepository.onTvShowsReceived());

        verify(remoteRepository, times(1)).getTvShows(any(TvShowsCallback.class), any(ConnectionCallback.class));

        assertEquals(tvShows.size(), Objects.requireNonNull(tvShowEntities).size());
    }

    @Test
    public void getTvShow() {

    }
}