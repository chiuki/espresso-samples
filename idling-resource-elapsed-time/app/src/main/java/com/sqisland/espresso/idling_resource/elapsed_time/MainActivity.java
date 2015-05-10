package com.sqisland.espresso.idling_resource.elapsed_time;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
  private Button toggleButton;
  private TextView elapsedTimeView;
  private TextView resultView;

  private long startTime = -1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toggleButton = (Button) findViewById(R.id.toggle_button);
    elapsedTimeView = (TextView) findViewById(R.id.elapsed_Time);
    resultView = (TextView) findViewById(R.id.result);
  }

  public void toggle(View v) {
    if (startTime == -1) {
      toggleButton.setText(R.string.stop);
      elapsedTimeView.setText(null);
      resultView.setText(null);
      startTime = System.currentTimeMillis();
    } else {
      long elapsed = System.currentTimeMillis() - startTime;
      elapsedTimeView.setText(getString(R.string.elapsed_time, elapsed / 1000f));
      toggleButton.setText(R.string.start);
      resultView.setText(elapsed >= DateUtils.MINUTE_IN_MILLIS ?
          R.string.success : R.string.failure);
      startTime = -1;
    }
  }
}