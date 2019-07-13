package com.arieftb.tonton.repo.callback;

import com.arieftb.tonton.model.entity.MovieEntity;

public interface MovieCallback {
    void onMovieReceived(MovieEntity movieEntity);
}
