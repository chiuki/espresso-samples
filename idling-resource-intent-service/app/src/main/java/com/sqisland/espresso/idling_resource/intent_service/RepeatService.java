package com.sqisland.espresso.idling_resource.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;

public class RepeatService extends IntentService {
  public RepeatService() {
    super(RepeatService.class.getName());
  }

  public static final String INTENT_REPEAT
      = "com.sqisland.espressso.idling_resource.intent_service.REPEAT";
  public static final String KEY_INPUT = "input";
  public static final String KEY_OUTPUT = "output";

  @Override
  protected void onHandleIntent(Intent intent) {
    SystemClock.sleep(1000);  // Pretend it runs for a long time.

    String query = intent.getStringExtra(KEY_INPUT);

    Intent replyIntent = new Intent(INTENT_REPEAT);
    replyIntent.putExtra(KEY_OUTPUT, query + "\n" + query);

    LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
    manager.sendBroadcast(replyIntent);
  }
}