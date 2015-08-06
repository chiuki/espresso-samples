package com.sqisland.android.espresso.hello;

import android.app.Activity;
import android.os.Bundle;
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
}