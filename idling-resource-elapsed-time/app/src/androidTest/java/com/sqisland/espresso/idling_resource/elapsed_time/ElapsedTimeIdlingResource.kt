package com.sqisland.espresso.idling_resource.elapsed_time

import android.support.test.espresso.IdlingResource

class ElapsedTimeIdlingResource(private val waitingTime: Long) : IdlingResource {
  private val startTime: Long = System.currentTimeMillis()
  private var resourceCallback: IdlingResource.ResourceCallback? = null

  override fun getName() =
    ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime

  override fun isIdleNow(): Boolean {
    val elapsed = System.currentTimeMillis() - startTime
    val idle = elapsed >= waitingTime
    if (idle) {
      resourceCallback?.onTransitionToIdle()
    }
    return idle
  }

  override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
    this.resourceCallback = resourceCallback
  }
}