package com.sqisland.espresso.repeatedly_until;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.repeatedlyUntil;

public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void eleven() {
    onView(withId(R.id.number))
        .check(matches(withText("0")));

    onView(withId(R.id.number))
        .perform(repeatedlyUntil(click(), withText("11"), 20));

    onView(withId(R.id.squared))
        .check(matches(withText("121")));
  }
}