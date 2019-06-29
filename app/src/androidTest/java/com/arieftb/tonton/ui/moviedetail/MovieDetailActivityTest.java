/*
 * Developed by arieftb on 6/29/19 2:42 PM.
 * Last Modified 6/29/19 2:42 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.ui.moviedetail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.Movie;
import com.arieftb.tonton.utils.Dummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MovieDetailActivityTest {

    private final Movie movieDummy = Dummy.getDummyMovies().get(0);
    private String movieData;

    @Rule
    public ActivityTestRule<MovieDetailActivity> activityTestRule = new ActivityTestRule<MovieDetailActivity>(MovieDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context destinationContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(destinationContext, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.MOVIE_ID, movieDummy.getId());

            return intent;
        }
    };

    @Before
    public void setUp() {
        movieData = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.text_movie_data);
    }


    @Test
    public void loadMovieDetail() throws InterruptedException {
//        Check Title
        onView(withId(R.id.text_movie_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_movie_detail_title)).check(matches(withText(movieDummy.getTitle())));

//        Check Rating
        onView(withId(R.id.text_movie_detail_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.text_movie_detail_rating)).check(matches(withText(movieDummy.getRating().toString())));

//        Check Image Banner & Poster
        onView(withId(R.id.image_movie_detail_banner)).check(matches(isDisplayed()));
        onView(withId(R.id.image_movie_detail_poster)).check(matches(isDisplayed()));

//        Check Data
        onView(withId(R.id.text_movie_detail_data)).check(matches(isDisplayed()));
        onView(withId(R.id.text_movie_detail_data)).check(matches(withText(String.format(movieData, movieDummy.getGenre(), movieDummy.getReleaseDate()))));

//        Check Description
        onView(withId(R.id.text_movie_detail_description)).check(matches(isDisplayed()));
        onView(withId(R.id.text_movie_detail_description)).check(matches(withText(movieDummy.getDescription())));

//        Show 3 seconds
        Thread.sleep(3000);
    }
}