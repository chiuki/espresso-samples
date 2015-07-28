package com.sqisland.espresso.idling_resource.dialog_fragment;

import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;

public class DialogFragmentIdlingResource implements IdlingResource {
  private final FragmentManager manager;
  private final String tag;
  private ResourceCallback resourceCallback;

  public DialogFragmentIdlingResource(FragmentManager manager, String tag) {
    this.manager = manager;
    this.tag = tag;
  }

  @Override
  public String getName() {
    return DialogFragmentIdlingResource.class.getName() + ":" + tag;
  }

  @Override
  public boolean isIdleNow() {
    boolean idle = (manager.findFragmentByTag(tag) == null);
    if (idle) {
      resourceCallback.onTransitionToIdle();
    }
    return idle;
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
    this.resourceCallback = resourceCallback;
  }
}