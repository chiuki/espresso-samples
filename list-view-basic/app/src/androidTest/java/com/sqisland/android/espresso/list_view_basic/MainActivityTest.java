package com.sqisland.android.espresso.list_view_basic;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void clickItem() {
    onView(withId(R.id.text))
        .check(matches(not(isDisplayed())));

    onData(withValue(27))
        .inAdapterView(withId(R.id.list))
        .perform(click());

    onView(withId(R.id.text))
        .check(matches(withText("27")))
        .check(matches(isDisplayed()));
  }

  public static Matcher<Object> withValue(final int value) {
    return new BoundedMatcher<Object, MainActivity.Item>(MainActivity.Item.class) {
      @Override
      public void describeTo(Description description) {
        description.appendText("has value " + value);
      }
      @Override
      public boolean matchesSafely(MainActivity.Item item) {
        return item.toString().equals(String.valueOf(value));
      }
    };
  }
}