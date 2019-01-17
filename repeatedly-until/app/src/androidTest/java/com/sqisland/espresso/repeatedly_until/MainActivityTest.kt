package com.sqisland.espresso.repeatedly_until

import android.support.test.rule.ActivityTestRule

import org.junit.Rule
import org.junit.Test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.action.ViewActions.repeatedlyUntil

class MainActivityTest {
  @get:Rule
  val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

  @Test
  fun eleven() {
    onView(withId(R.id.number))
      .check(matches(withText("0")))

    onView(withId(R.id.number))
      .perform(repeatedlyUntil(click(), withText("11"), 20))

    onView(withId(R.id.squared))
      .check(matches(withText("121")))
  }
}