package com.sqisland.espresso.rotate_screen

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  val activityRule = ActivityTestRule(MainActivity::class.java)

  @Before
  fun checkInitialCount() {
    onView(withId(R.id.count))
      .check(matches(withText("0")))
  }

  @Test
  fun increment() {
    onView(withId(R.id.increment_button))
      .check(matches(withText(R.string.increment)))
      .perform(click())
    onView(withId(R.id.count))
      .check(matches(withText("1")))
  }

  @Test
  fun incrementTwiceAndRotateScreen() {
    onView(withId(R.id.increment_button))
      .check(matches(withText(R.string.increment)))
      .perform(click())
      .perform(click())
    onView(withId(R.id.count))
      .check(matches(withText("2")))

    rotateScreen()

    onView(withId(R.id.count))
      .check(matches(withText("2")))
  }

  @Test
  fun noIncrementRotateScreen() {
    rotateScreen()
    onView(withId(R.id.count))
      .check(matches(withText("0")))
  }

  private fun rotateScreen() {
    val context = InstrumentationRegistry.getTargetContext()
    val orientation = context.resources.configuration.orientation

    val activity = activityRule.activity
    activity.requestedOrientation = if (orientation == Configuration.ORIENTATION_PORTRAIT)
      ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    else
      ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
  }
}