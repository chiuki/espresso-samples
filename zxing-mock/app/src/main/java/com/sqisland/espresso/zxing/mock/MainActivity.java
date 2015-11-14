package com.sqisland.espresso.zxing.mock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private static final int RESULT_CODE = 2000;

  ZXingBridge bridge = Injection.provideZXingBridge();

  private TextView formatView;
  private TextView resultView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    View scanButton = findViewById(R.id.scan_button);
    formatView = (TextView) findViewById(R.id.format);
    resultView = (TextView) findViewById(R.id.result);

    if (savedInstanceState != null) {
      formatView.setText(savedInstanceState.getCharSequence(ZXingBridge.KEY_SCAN_RESULT_FORMAT));
      resultView.setText(savedInstanceState.getCharSequence(ZXingBridge.KEY_SCAN_RESULT));
    }

    if (bridge.isSupported(this)) {
      ZXingBridge.Listener listener = new ZXingBridge.Listener() {
        @Override
        public void onScanResult(String format, String result) {
          formatView.setText(format);
          resultView.setText(result);
        }
      };
      bridge.setListener(listener);

      scanButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          bridge.scan(MainActivity.this, RESULT_CODE);
        }
      });
    } else {
      scanButton.setVisibility(View.GONE);
      formatView.setVisibility(View.GONE);
      resultView.setText(R.string.not_supported);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case RESULT_CODE:
        bridge.onActivityResult(resultCode, data);
        break;
      default:
        super.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putCharSequence(ZXingBridge.KEY_SCAN_RESULT_FORMAT, formatView.getText());
    outState.putCharSequence(ZXingBridge.KEY_SCAN_RESULT, resultView.getText());
  }
}