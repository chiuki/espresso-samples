package com.sqisland.espresso.assume

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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