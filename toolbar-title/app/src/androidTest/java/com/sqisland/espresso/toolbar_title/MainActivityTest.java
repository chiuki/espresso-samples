package com.sqisland.espresso.toolbar_title;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void toolbarTitle() {
    CharSequence title = InstrumentationRegistry.getTargetContext().getString(R.string.my_title);
    matchToolbarTitle(title);
  }

  private static ViewInteraction matchToolbarTitle(CharSequence title) {
    return onView(
        allOf(
            withParent(isAssignableFrom(Toolbar.class)),
            isAssignableFrom(TextView.class)))
        .check(matches(withText(title.toString())));
  }
}