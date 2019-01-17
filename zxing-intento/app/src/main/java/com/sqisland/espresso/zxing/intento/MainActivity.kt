package com.sqisland.espresso.zxing.intento

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
  private lateinit var formatView: TextView
  private lateinit var resultView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    formatView = findViewById(R.id.format)
    resultView = findViewById(R.id.result)

    savedInstanceState?.let {
      formatView.text = it.getCharSequence(KEY_SCAN_RESULT_FORMAT)
      resultView.text = it.getCharSequence(KEY_SCAN_RESULT)
    }

    val scanButton = findViewById<View>(R.id.scan_button)
    if (isZXingSupported(this)) {
      scanButton.setOnClickListener {
        val intent = Intent(ACTION_ZXING_SCAN)
        startActivityForResult(intent, RESULT_CODE)
      }
    } else {
      scanButton.visibility = View.GONE
      resultView.setText(R.string.not_supported)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == RESULT_CODE) {
      if (resultCode == Activity.RESULT_OK) {
        val result = data!!.getStringExtra(KEY_SCAN_RESULT)
        val format = data.getStringExtra(KEY_SCAN_RESULT_FORMAT)
        formatView.text = format
        resultView.text = result
      }
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putCharSequence(KEY_SCAN_RESULT_FORMAT, formatView.text)
    outState.putCharSequence(KEY_SCAN_RESULT, resultView.text)
  }

  companion object {
    val ACTION_ZXING_SCAN = "com.google.zxing.client.android.SCAN"
    val KEY_SCAN_RESULT = "SCAN_RESULT"
    val KEY_SCAN_RESULT_FORMAT = "SCAN_RESULT_FORMAT"

    private val RESULT_CODE = 2000

    fun isZXingSupported(context: Context): Boolean {
      val intent = Intent(ACTION_ZXING_SCAN)
      val packageManager = context.packageManager
      val list = packageManager.queryIntentActivities(
        intent, PackageManager.MATCH_DEFAULT_ONLY)
      return list.size > 0
    }
  }
}