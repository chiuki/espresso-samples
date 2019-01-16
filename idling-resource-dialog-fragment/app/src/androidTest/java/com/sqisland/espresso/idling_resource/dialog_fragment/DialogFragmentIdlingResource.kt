package com.sqisland.espresso.idling_resource.dialog_fragment

import android.support.test.espresso.IdlingResource
import android.support.v4.app.FragmentManager

class DialogFragmentIdlingResource(private val manager: FragmentManager, private val tag: String) : IdlingResource {
  private var resourceCallback: IdlingResource.ResourceCallback? = null

  override fun getName() = DialogFragmentIdlingResource::class.java.name + ":" + tag

  override fun isIdleNow(): Boolean {
    val idle = manager.findFragmentByTag(tag) == null
    if (idle) {
      resourceCallback?.onTransitionToIdle()
    }
    return idle
  }

  override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
    this.resourceCallback = resourceCallback
  }
}