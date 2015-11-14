package com.sqisland.espresso.zxing.mock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  public static final String KEY_SCAN_RESULT = "SCAN_RESULT";
  public static final String KEY_SCAN_RESULT_FORMAT = "SCAN_RESULT_FORMAT";

  private static final int RESULT_CODE = 2000;

  private TextView formatView;
  private TextView resultView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    formatView = (TextView) findViewById(R.id.format);
    resultView = (TextView) findViewById(R.id.result);

    if (savedInstanceState != null) {
      formatView.setText(savedInstanceState.getCharSequence(KEY_SCAN_RESULT_FORMAT));
      resultView.setText(savedInstanceState.getCharSequence(KEY_SCAN_RESULT));
    }

    View scanButton = findViewById(R.id.scan_button);
    scanButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        startActivityForResult(intent, RESULT_CODE);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == RESULT_CODE) {
      if (resultCode == RESULT_OK) {
        String result = data.getStringExtra(KEY_SCAN_RESULT);
        String format = data.getStringExtra(KEY_SCAN_RESULT_FORMAT);
        formatView.setText(format);
        resultView.setText(result);
      }
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putCharSequence(KEY_SCAN_RESULT_FORMAT, formatView.getText());
    outState.putCharSequence(KEY_SCAN_RESULT, resultView.getText());
  }
}