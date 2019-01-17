package com.sqisland.espresso.repeatedly_until

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.repeatedlyUntil
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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