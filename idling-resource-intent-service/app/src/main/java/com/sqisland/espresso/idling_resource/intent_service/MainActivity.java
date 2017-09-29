package com.sqisland.espresso.idling_resource.intent_service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
  private TextView input;
  private TextView output;

  private LocalBroadcastManager manager;
  private BroadcastReceiver receiver;
  private IntentFilter filter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    input = findViewById(R.id.input);
    output = findViewById(R.id.output);

    manager = LocalBroadcastManager.getInstance(this);
    receiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        output.setText(intent.getStringExtra(RepeatService.KEY_OUTPUT));
      }
    };
    filter = new IntentFilter(RepeatService.INTENT_REPEAT);
  }

  @Override
  protected void onResume() {
    super.onResume();
    manager.registerReceiver(receiver, filter);
  }

  @Override
  protected void onPause() {
    super.onPause();
    manager.unregisterReceiver(receiver);
  }

  public void repeat(View v) {
    Intent intent = new Intent(this, RepeatService.class);
    intent.putExtra(RepeatService.KEY_INPUT, input.getText().toString());
    startService(intent);
  }
}