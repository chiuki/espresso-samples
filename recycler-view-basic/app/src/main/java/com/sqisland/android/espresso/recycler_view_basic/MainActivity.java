package com.sqisland.android.espresso.recycler_view_basic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    final TextView textView = findViewById(R.id.text);
    textView.setBackgroundColor(Color.LTGRAY);
    textView.setVisibility(View.GONE);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new NumberedAdapter(30, new NumberedAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(int position) {
        textView.setText(String.valueOf(position));
        textView.setVisibility(View.VISIBLE);
      }
    }));
  }
}