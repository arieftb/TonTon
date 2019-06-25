/*
 * Developed by arieftb on 6/25/19 3:44 PM.
 * Last Modified 6/25/19 3:44 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.tvshow;

import androidx.lifecycle.ViewModel;

import com.arieftb.tonton.model.TvShow;
import com.arieftb.tonton.utils.Dummy;

import java.util.List;

public class TvShowsViewModel extends ViewModel {
    public List<TvShow> getTvShows() {
        return Dummy.getDummyTvShows();
    }
}
