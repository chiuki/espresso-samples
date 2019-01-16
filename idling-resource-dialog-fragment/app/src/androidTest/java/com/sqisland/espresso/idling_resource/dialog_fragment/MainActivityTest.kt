package com.sqisland.espresso.idling_resource.dialog_fragment

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  val activityRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun done() {
    val idlingResource = DialogFragmentIdlingResource(
      activityRule.getActivity().getSupportFragmentManager(),
      LoadingDialogFragment.TAG)

    IdlingRegistry.getInstance().register(idlingResource)

    onView(withId(R.id.text))
      .check(matches(withText(R.string.done)))

    IdlingRegistry.getInstance().unregister(idlingResource)
  }
}