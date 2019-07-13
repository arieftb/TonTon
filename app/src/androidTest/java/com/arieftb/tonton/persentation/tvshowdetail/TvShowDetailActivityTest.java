/*
 * Developed by arieftb on 6/29/19 3:11 PM.
 * Last Modified 6/29/19 3:11 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.tvshowdetail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.arieftb.tonton.R;
import com.arieftb.tonton.model.TvShow;
import com.arieftb.tonton.utils.Dummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class TvShowDetailActivityTest {

    private final TvShow tvShowDummy = Dummy.getDummyTvShows().get(0);
    private String tvShowData;

    @Rule
    public ActivityTestRule<TvShowDetailActivity> activityTestRule = new ActivityTestRule<TvShowDetailActivity>(TvShowDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context destinationContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(destinationContext, TvShowDetailActivity.class);
            intent.putExtra(TvShowDetailActivity.TV_SHOW_ID, tvShowDummy.getId());

            return intent;
        }
    };

    @Before
    public void setUp() {
        tvShowData = InstrumentationRegistry.getInstrumentation().getTargetContext().getString(R.string.text_tv_show_data);
    }

    @Test
    public void loadTvShowDetail() throws InterruptedException {
        //        Check Title
        onView(withId(R.id.text_tv_show_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_tv_show_detail_title)).check(matches(withText(tvShowDummy.getTitle())));

//        Check Rating
        onView(withId(R.id.text_tv_show_detail_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.text_tv_show_detail_rating)).check(matches(withText(tvShowDummy.getRating().toString())));

//        Check Image Banner & Poster
        onView(withId(R.id.image_tv_show_detail_banner)).check(matches(isDisplayed()));
        onView(withId(R.id.image_tv_show_detail_poster)).check(matches(isDisplayed()));

//        Check Data
        onView(withId(R.id.text_tv_show_detail_data)).check(matches(isDisplayed()));
        onView(withId(R.id.text_tv_show_detail_data)).check(matches(withText(String.format(tvShowData, tvShowDummy.getGenre(), tvShowDummy.getReleaseDate()))));

//        Check Description
        onView(withId(R.id.text_tv_show_detail_description)).check(matches(isDisplayed()));
        onView(withId(R.id.text_tv_show_detail_description)).check(matches(withText(tvShowDummy.getDescription())));


//        Show 3 seconds
        Thread.sleep(3000);
    }

}