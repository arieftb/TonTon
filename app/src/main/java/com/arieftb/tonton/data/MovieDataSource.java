/*
 * Developed by arieftb on 7/9/19 1:58 PM.
 * Last Modified 7/9/19 1:58 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.data;

import androidx.lifecycle.LiveData;

import com.arieftb.tonton.model.response.MoviesResponse;
import com.arieftb.tonton.model.response.ResultsItem;

import java.util.List;

public interface MovieDataSource {
    LiveData<List<ResultsItem>> getMovies();
}
