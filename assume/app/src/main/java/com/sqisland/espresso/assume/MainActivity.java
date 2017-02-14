package com.sqisland.espresso.assume;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    boolean isInTheUK = Locale.UK.equals(Locale.getDefault());
    View button = findViewById(R.id.button);
    button.setEnabled(isInTheUK);
  }
}