package com.sqisland.android.espresso.hello

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : Activity() {
  private lateinit var greetingView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    greetingView = findViewById(R.id.greeting)
  }

  fun greet(v: View) {
    greetingView.setText(R.string.hello)
  }
}