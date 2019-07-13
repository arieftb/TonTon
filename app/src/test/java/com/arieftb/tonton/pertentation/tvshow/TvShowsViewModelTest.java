/*
 * Developed by arieftb on 6/28/19 6:28 AM.
 * Last Modified 6/28/19 6:28 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.pertentation.tvshow;

import com.arieftb.tonton.model.TvShow;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TvShowsViewModelTest {

    private TvShowsViewModel tvShowsViewModel;

    @Before
    public void setTvShowsViewModel() {
        tvShowsViewModel = new TvShowsViewModel();
    }

    @Test
    public void getTvShows() {
        List<TvShow> tvShows = tvShowsViewModel.getTvShows();
        assertNotNull(tvShows);
        assertEquals(19, tvShows.size());
    }
}