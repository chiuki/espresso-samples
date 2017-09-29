package com.sqisland.espresso.idling_resource.dialog_fragment;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void done() {
    IdlingResource idlingResource = new DialogFragmentIdlingResource(
        activityRule.getActivity().getSupportFragmentManager(),
        LoadingDialogFragment.TAG);

    IdlingRegistry.getInstance().register(idlingResource);

    onView(withId(R.id.text))
        .check(matches(withText(R.string.done)));

    IdlingRegistry.getInstance().unregister(idlingResource);
  }
}