package com.sqisland.espresso.idling_resource.elapsed_time

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.text.format.DateUtils
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  val activityRule = ActivityTestRule(MainActivity::class.java)

  @Before
  fun resetTimeout() {
    IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS)
    IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS)
  }

  @Test
  fun waitFor8Seconds() {
    waitFor(DateUtils.SECOND_IN_MILLIS * 8, false)
  }

  @Test
  fun waitFor75Seconds() {
    waitFor(DateUtils.SECOND_IN_MILLIS * 75, true)
  }

  private fun waitFor(waitingTime: Long, success: Boolean) {
    // Start
    onView(withId(R.id.toggle_button))
      .check(matches(withText(R.string.start)))
      .perform(click())

    // Make sure Espresso does not time out
    IdlingPolicies.setMasterPolicyTimeout(waitingTime * 2, TimeUnit.MILLISECONDS)
    IdlingPolicies.setIdlingResourceTimeout(waitingTime * 2, TimeUnit.MILLISECONDS)

    // Now we wait
    val idlingResource = ElapsedTimeIdlingResource(waitingTime)
    IdlingRegistry.getInstance().register(idlingResource)

    // Stop and verify
    onView(withId(R.id.toggle_button))
      .check(matches(withText(R.string.stop)))
      .perform(click())
    onView(withId(R.id.result))
      .check(matches(withText(if (success) R.string.success else R.string.failure)))

    // Clean up
    IdlingRegistry.getInstance().unregister(idlingResource)
  }
}