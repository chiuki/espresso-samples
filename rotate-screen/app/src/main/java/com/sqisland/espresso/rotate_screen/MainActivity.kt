package com.sqisland.espresso.rotate_screen

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  private var count = 0
  private lateinit var countView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    countView = findViewById(R.id.count)

    savedInstanceState?.let {
      count = it.getInt(KEY_COUNT, 0)
    }

    updateCount()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putInt(KEY_COUNT, count)
  }

  fun increment(v: View) {
    count += 1
    updateCount()
  }

  private fun updateCount() {
    countView.text = count.toString()
  }

  companion object {
    private const val KEY_COUNT = "count"
  }
}