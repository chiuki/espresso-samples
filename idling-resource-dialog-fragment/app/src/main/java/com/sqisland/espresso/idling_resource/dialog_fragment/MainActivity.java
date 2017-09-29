package com.sqisland.espresso.idling_resource.dialog_fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textView = findViewById(R.id.text);

    LoadingDialogFragment fragment = new LoadingDialogFragment();
    fragment.setCancelable(false);
    fragment.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
  }

  public void onLoadingFinished() {
    textView.setText(R.string.done);
  }
}