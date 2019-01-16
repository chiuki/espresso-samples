package com.sqisland.android.espresso.list_view_basic

import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.not

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  val activityRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun clickItem() {
    onView(withId(R.id.text))
      .check(matches(not(isDisplayed())))

    onData(withValue(27))
      .inAdapterView(withId(R.id.list))
      .perform(click())

    onView(withId(R.id.text))
      .check(matches(withText("27")))
      .check(matches(isDisplayed()))
  }

  companion object {
    fun withValue(value: Int): Matcher<Any> {
      return object : BoundedMatcher<Any, MainActivity.Item>(MainActivity.Item::class.java) {
        override fun describeTo(description: Description) {
          description.appendText("has value $value")
        }
        override fun matchesSafely(item: MainActivity.Item): Boolean {
          return item.toString() == value.toString()
        }
      }
    }
  }
}