package com.sqisland.espresso.zxing.intento;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  public static final String ACTION_ZXING_SCAN = "com.google.zxing.client.android.SCAN";
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
    if (isZXingSupported(this)) {
      scanButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(ACTION_ZXING_SCAN);
          startActivityForResult(intent, RESULT_CODE);
        }
      });
    } else {
      scanButton.setVisibility(View.GONE);
    }
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

  public static boolean isZXingSupported(Context context) {
    Intent intent = new Intent(ACTION_ZXING_SCAN);
    PackageManager packageManager = context.getPackageManager();
    List<ResolveInfo> list = packageManager.queryIntentActivities(
        intent, PackageManager.MATCH_DEFAULT_ONLY);
    return list.size() > 0;
  }
}