package com.sqisland.espresso.assume

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import java.util.Locale

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val isInTheUK = Locale.UK == Locale.getDefault()
    val button = findViewById<View>(R.id.button)
    button.isEnabled = isInTheUK
  }
}