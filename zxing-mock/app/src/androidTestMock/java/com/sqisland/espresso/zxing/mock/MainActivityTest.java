package com.sqisland.espresso.zxing.mock;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
      MainActivity.class,
      true,     // initialTouchMode
      false);   // launchActivity. False to set up mocks before activity launch

  @Test
  public void notSupported() {
    ZXingBridge bridge = Injection.provideZXingBridge();

    Mockito.when(bridge.isSupported(Mockito.any(Activity.class))).thenReturn(false);

    activityRule.launchActivity(null);

    onView(withId(R.id.scan_button))
        .check(matches(not(isDisplayed())));
    onView(withId(R.id.format))
        .check(matches(not(isDisplayed())));
    onView(withId(R.id.result))
        .check(matches(withText(R.string.not_supported)));
  }

  @Test
  public void scan() {
    final String format = "EAN_13";
    final String result = "9781449328214";

    ZXingBridge bridge = Injection.provideZXingBridge();

    Mockito.when(bridge.isSupported(Mockito.any(Activity.class))).thenReturn(true);

    final ArgumentCaptor<ZXingBridge.Listener> captor
        = ArgumentCaptor.forClass(ZXingBridge.Listener.class);
    Mockito.doNothing()
        .when(bridge).setListener(captor.capture());
    Mockito.doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        ZXingBridge.Listener listener = captor.getValue();
        assertNotNull(listener);
        listener.onScanResult(format, result);
        return null;
      }
    }).when(bridge).scan(Mockito.any(Activity.class), Mockito.anyInt());

    activityRule.launchActivity(null);

    onView(withId(R.id.scan_button))
        .check(matches(isDisplayed()))
        .perform(click());

    onView(withId(R.id.format))
        .check(matches(withText(format)));
    onView(withId(R.id.result))
        .check(matches(withText(result)));
  }
}