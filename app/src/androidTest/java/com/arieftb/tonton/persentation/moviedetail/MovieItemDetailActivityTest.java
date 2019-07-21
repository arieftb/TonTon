/*
 * Developed by arieftb on 6/29/19 2:42 PM.
 * Last Modified 6/29/19 2:42 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.moviedetail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.entity.MovieEntity;
import com.arieftb.tonton.utils.DataDummy;
import com.arieftb.tonton.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MovieItemDetailActivityTest {

    private final MovieEntity movieDummy = DataDummy.getMovie(429617);
    private String movieData;

    @Rule
    public ActivityTestRule<MovieDetailActivity> activityTestRule = new ActivityTestRule<MovieDetailActivity>(MovieDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context destinationContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(destinationContext, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.MOVIE_ID, Objects.requireNonNull(movieDummy).getId());

            return intent;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        movieData = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.text_movie_data);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovieDetail() {
//        Check Title
        onView(withId(R.id.text_movie_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_movie_detail_title)).check(matches(withText(Objects.requireNonNull(movieDummy).getTitle())));

//        Check Rating
        onView(withId(R.id.text_movie_detail_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.text_movie_detail_rating)).check(matches(withText(String.valueOf(movieDummy.getVoteAverage()))));

//        Check Image Banner & Poster
        onView(withId(R.id.image_movie_detail_banner)).check(matches(isDisplayed()));
        onView(withId(R.id.image_movie_detail_poster)).check(matches(isDisplayed()));

//        Check Data
        onView(withId(R.id.text_movie_detail_data)).check(matches(isDisplayed()));
        onView(withId(R.id.text_movie_detail_data)).check(matches(withText(String.format(movieData, movieDummy.getLang(), movieDummy.getReleaseDate()))));

//        Check Description
        onView(withId(R.id.text_movie_detail_description)).check(matches(isDisplayed()));
        onView(withId(R.id.text_movie_detail_description)).check(matches(withText(movieDummy.getOverview())));
    }
}