package com.sqisland.espresso.idling_resource.okhttp;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void name() {
    IdlingResource idlingResource = OkHttp3IdlingResource.create(
        "okhttp", OkHttpProvider.getOkHttpInstance());
    IdlingRegistry.getInstance().register(idlingResource);

    onView(withId(R.id.name))
        .check(matches(withText("Chiu-Ki Chan")));

    IdlingRegistry.getInstance().unregister(idlingResource);
  }
}