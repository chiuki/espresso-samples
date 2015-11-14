package com.sqisland.espresso.zxing;

import android.app.Activity;
import android.content.Intent;

public class ZXingBridge {
  public static final String KEY_SCAN_RESULT = "SCAN_RESULT";
  public static final String KEY_SCAN_RESULT_FORMAT = "SCAN_RESULT_FORMAT";

  private Listener listener;

  public void setListener(Listener listener) {
    this.listener = listener;
  }

  public void scan(Activity activity, int resultCode) {
    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
    activity.startActivityForResult(intent, resultCode);
  }

  public void onActivityResult(int resultCode, Intent data) {
    if (listener == null) {
      return;
    }

    if (resultCode != Activity.RESULT_OK) {
      return;
    }

    String format = data.getStringExtra(KEY_SCAN_RESULT_FORMAT);
    String result = data.getStringExtra(KEY_SCAN_RESULT);
    listener.onScanResult(format, result);
  }

  public interface Listener {
    void onScanResult(String format, String result);
  }
}