package com.sqisland.espresso.zxing.intento

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assume
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  val activityRule = ActivityTestRule(
    MainActivity::class.java,
    true,  // initialTouchMode
    false)   // launchActivity. False to set up mocks before activity launch

  @Test
  fun scan() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    Assume.assumeTrue(MainActivity.isZXingSupported(context))

    val format = "EAN_13"
    val result = "9781449328214"

    Intents.init()

    activityRule.launchActivity(null)

    val data = Intent()

    data.putExtra(MainActivity.KEY_SCAN_RESULT_FORMAT, format)
    data.putExtra(MainActivity.KEY_SCAN_RESULT, result)
    val activityResult = Instrumentation.ActivityResult(
      Activity.RESULT_OK, data)

    intending(hasAction(MainActivity.ACTION_ZXING_SCAN))
      .respondWith(activityResult)

    onView(withId(R.id.scan_button))
      .check(matches(isDisplayed()))
      .perform(click())

    onView(withId(R.id.format))
      .check(matches(withText(format)))
    onView(withId(R.id.result))
      .check(matches(withText(result)))

    Intents.release()
  }
}