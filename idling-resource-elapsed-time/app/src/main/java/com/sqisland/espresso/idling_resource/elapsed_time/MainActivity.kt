package com.sqisland.espresso.idling_resource.elapsed_time

import android.app.Activity
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity() {
  private lateinit var toggleButton: Button
  private lateinit var elapsedTimeView: TextView
  private lateinit var resultView: TextView

  private var startTime = -1L

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    toggleButton = findViewById(R.id.toggle_button)
    elapsedTimeView = findViewById(R.id.elapsed_Time)
    resultView = findViewById(R.id.result)
  }

  fun toggle(v: View) {
    if (startTime < 0) {
      toggleButton.setText(R.string.stop)
      elapsedTimeView.text = null
      resultView.text = null
      startTime = System.currentTimeMillis()
    } else {
      val elapsed = System.currentTimeMillis() - startTime
      elapsedTimeView.text = getString(R.string.elapsed_time, elapsed / 1000f)
      toggleButton.setText(R.string.start)
      resultView.setText(if (elapsed >= DateUtils.MINUTE_IN_MILLIS)
        R.string.success
      else
        R.string.failure)
      startTime = -1
    }
  }
}