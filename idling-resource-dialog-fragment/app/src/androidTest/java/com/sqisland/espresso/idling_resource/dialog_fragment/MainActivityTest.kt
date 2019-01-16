package com.sqisland.espresso.idling_resource.dialog_fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
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