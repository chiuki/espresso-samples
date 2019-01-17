package com.sqisland.espresso.repeatedly_until


import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val numberView = findViewById<TextView>(R.id.number)
    val squaredView = findViewById<TextView>(R.id.squared)

    numberView.text = "0"
    squaredView.text = "0"

    numberView.setOnClickListener {
      var number = Integer.parseInt(numberView.getText().toString())
      number += 1
      val squared = number * number
      numberView.text = number.toString()
      squaredView.text = squared.toString()
    }
  }
}