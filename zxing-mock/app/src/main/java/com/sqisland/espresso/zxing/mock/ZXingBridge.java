package com.sqisland.espresso.zxing.mock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public class ZXingBridge {
  public static final String KEY_SCAN_RESULT = "SCAN_RESULT";
  public static final String KEY_SCAN_RESULT_FORMAT = "SCAN_RESULT_FORMAT";

  protected Listener listener;
  public void setListener(Listener listener) {
    this.listener = listener;
  }

  public boolean isSupported(Context context) {
    final PackageManager packageManager = context.getPackageManager();
    List<ResolveInfo> list = packageManager.queryIntentActivities(
        getIntent(), PackageManager.MATCH_DEFAULT_ONLY);
    return list.size() > 0;
  }

  public void scan(Activity activity, int resultCode) {
    Intent intent = getIntent();
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

  private static Intent getIntent() {
    return new Intent("com.google.zxing.client.android.SCAN");
  }

  public interface Listener {
    void onScanResult(String format, String result);
  }
}