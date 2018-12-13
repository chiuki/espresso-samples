package com.sqisland.espresso.idling_resource.dialog_fragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.format.DateUtils

import java.lang.ref.WeakReference

class LoadingDialogFragment : DialogFragment() {
  private val handler = LoadingHandler(this)

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    handler.sendEmptyMessageDelayed(LoadingHandler.MSG_DISMISS, DELAY)

    return AlertDialog.Builder(activity!!)
      .setTitle(R.string.loading)
      .setMessage(R.string.please_wait)
      .create()
  }

  override fun onDestroyView() {
    handler.removeMessages(LoadingHandler.MSG_DISMISS)
    super.onDestroyView()
  }

  private class LoadingHandler(fragment: DialogFragment) : Handler() {
    private val ref = WeakReference(fragment)

    override fun handleMessage(msg: Message) {
      ref.get()?.let { fragment ->
        fragment.dismiss()
        val activity = fragment.activity
        if (activity is MainActivity) {
          activity.onLoadingFinished()
        }
      }
    }

    companion object {
      const val MSG_DISMISS = 0
    }
  }

  companion object {
    const val TAG = "Loading"
    private const val DELAY = DateUtils.SECOND_IN_MILLIS * 3
  }
}