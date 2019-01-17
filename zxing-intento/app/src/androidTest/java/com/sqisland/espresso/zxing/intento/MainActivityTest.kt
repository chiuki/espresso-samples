package com.sqisland.espresso.zxing.intento

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
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
    val context = InstrumentationRegistry.getTargetContext()
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