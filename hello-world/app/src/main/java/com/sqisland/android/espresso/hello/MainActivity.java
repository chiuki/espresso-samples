package com.sqisland.android.espresso.hello;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
  private TextView greetingView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    greetingView = (TextView) findViewById(R.id.greeting);
  }

  public void greet(View v) {
    greetingView.setText(R.string.hello);
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.i("sqisland", "onStart: " + this);
  }

  @Override
  protected void onStop() {
    Log.i("sqisland", "onStop: " + this);
    super.onStop();
  }
}