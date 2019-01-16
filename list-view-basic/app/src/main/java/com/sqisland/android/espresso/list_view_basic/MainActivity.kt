package com.sqisland.android.espresso.list_view_basic

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    val textView = findViewById<TextView>(R.id.text)
    textView.setBackgroundColor(Color.LTGRAY)
    textView.setVisibility(View.GONE)

    val listView = findViewById<ListView>(R.id.list)

    val items = arrayOfNulls<Item>(COUNT)
    for (i in 0 until COUNT) {
      items[i] = Item(i)
    }
    listView.adapter = ArrayAdapter<Item>(this,
      android.R.layout.simple_list_item_1, items)

    listView.setOnItemClickListener { _, _, position, _ ->
      textView.text = items[position].toString()
      textView.visibility = View.VISIBLE
    }
  }

  class Item(private val value: Int) {
    override fun toString(): String {
      return value.toString()
    }
  }

  companion object {
    private const val COUNT = 30
  }
}
