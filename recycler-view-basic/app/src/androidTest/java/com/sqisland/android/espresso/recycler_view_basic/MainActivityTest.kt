package com.sqisland.android.espresso.recycler_view_basic

import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.widget.RecyclerView

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

  @Test
  fun clickItem() {
    onView(withId(R.id.recycler_view))
      .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(27, click()))

    onView(withId(R.id.text))
      .check(matches(withText("27")))
      .check(matches(isDisplayed()))
  }
}