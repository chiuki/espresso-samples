package com.sqisland.espresso.repeatedly_until;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final TextView numberView = findViewById(R.id.number);
    final TextView squaredView = findViewById(R.id.squared);

    numberView.setText("0");
    squaredView.setText("0");

    numberView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int number = Integer.parseInt(numberView.getText().toString());
        number += 1;
        int squared = number * number;
        numberView.setText(String.valueOf(number));
        squaredView.setText(String.valueOf(squared));
      }
    });
  }
}