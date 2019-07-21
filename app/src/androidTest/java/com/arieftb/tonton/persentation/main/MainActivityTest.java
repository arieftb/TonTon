/*
 * Developed by arieftb on 6/29/19 1:51 PM.
 * Last Modified 6/29/19 1:51 PM.
 * Copyright (c) 2019. All rights reserved.
 * www.arieftb.com
 */

package com.arieftb.tonton.persentation.main;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.arieftb.tonton.R;
import com.arieftb.tonton.utils.EspressoIdlingResource;
import com.arieftb.tonton.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void testAppBehaviour() throws InterruptedException {
        onView(withId(R.id.recycler_movies_list)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_main_bottom)).check(matches(isDisplayed()));

        onView(withId(R.id.recycler_movies_list)).check(new RecyclerViewItemCountAssertion(20));


        onView(withId(R.id.recycler_movies_list)).perform(RecyclerViewActions.scrollToPosition(17));


        onView(withId(R.id.recycler_movies_list)).perform(RecyclerViewActions.actionOnItemAtPosition(17, click()));


        pressBack();

        onView(withId(R.id.recycler_movies_list)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_main_bottom)).check(matches(isDisplayed()));
        onView(withId(R.id.menu_tv_shows)).perform(click());

        onView(withId(R.id.recycler_tv_shows_list)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_tv_shows_list)).check(new RecyclerViewItemCountAssertion(20));


        onView(withId(R.id.recycler_tv_shows_list)).perform(RecyclerViewActions.scrollToPosition(18));

        onView(withId(R.id.recycler_tv_shows_list)).perform(RecyclerViewActions.actionOnItemAtPosition(18, click()));


        pressBack();

        onView(withId(R.id.recycler_tv_shows_list)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_main_bottom)).check(matches(isDisplayed()));

    }
}