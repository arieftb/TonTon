/*
 * Developed by arieftb on 6/28/19 6:37 AM.
 * Last Modified 6/28/19 6:37 AM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshowdetail;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.TvShow;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TvShowDetailViewModelTest {

    private TvShowDetailViewModel viewModel;
    private TvShow tvShowTest;

    @Before
    public void setViewModel() {
        viewModel = new TvShowDetailViewModel();
        tvShowTest = new TvShow(1, "Arrow", "Crime", "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.", "October 10, 2012", 5.8, R.drawable.poster_arrow);
    }

    @Test
    public void getTvShowDetail() {
        viewModel.setTvShowId(tvShowTest.getId());
        TvShow tvShow = viewModel.getTvShowDetail();
        
        assertNotNull(tvShow);
        assertEquals(tvShowTest.getId(), tvShow.getId());
        assertEquals(tvShowTest.getTitle(), tvShow.getTitle());
        assertEquals(tvShowTest.getGenre(), tvShow.getGenre());
        assertEquals(tvShowTest.getDescription(), tvShow.getDescription());
        assertEquals(tvShowTest.getReleaseDate(), tvShow.getReleaseDate());
        assertEquals(tvShowTest.getRating(), tvShow.getRating());
        assertEquals(tvShowTest.getPoster(), tvShow.getPoster());
    }
}