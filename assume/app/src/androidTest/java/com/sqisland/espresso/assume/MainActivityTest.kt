package com.sqisland.espresso.assume

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assume
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  val activityRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun buttonShouldBeEnabledInUK() {
    Assume.assumeTrue(Locale.UK == Locale.getDefault())
    onView(withId(R.id.button))
      .check(matches(isEnabled()))
  }
}