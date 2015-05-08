package com.sqisland.espresso.toolbar_title;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static org.hamcrest.core.Is.is;

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
    return onView(isAssignableFrom(Toolbar.class))
        .check(matches(withToolbarTitle(is(title))));
  }

  private static Matcher<Object> withToolbarTitle(final Matcher<CharSequence> textMatcher) {
    return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
      @Override public boolean matchesSafely(Toolbar toolbar) {
        return textMatcher.matches(toolbar.getTitle());
      }
      @Override public void describeTo(Description description) {
        description.appendText("with toolbar title: ");
        textMatcher.describeTo(description);
      }
    };
  }
}