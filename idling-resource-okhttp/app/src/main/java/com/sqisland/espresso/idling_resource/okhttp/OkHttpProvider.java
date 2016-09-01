package com.sqisland.espresso.idling_resource.okhttp;


import okhttp3.OkHttpClient;

public abstract class OkHttpProvider {
  private static OkHttpClient instance = null;

  public static OkHttpClient getOkHttpInstance() {
    if (instance == null) {
      instance = new OkHttpClient();
    }
    return instance;
  }
}