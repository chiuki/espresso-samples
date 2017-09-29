package com.sqisland.espresso.idling_resource.dialog_fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;

import java.lang.ref.WeakReference;

public class LoadingDialogFragment extends DialogFragment {
  public static final String TAG = "Loading";

  private static final long DELAY = DateUtils.SECOND_IN_MILLIS * 3;

  private final LoadingHandler handler;

  public LoadingDialogFragment() {
    this.handler = new LoadingHandler(this);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    handler.sendEmptyMessageDelayed(LoadingHandler.MSG_DISMISS, DELAY);

    return new AlertDialog.Builder(getActivity())
        .setTitle(R.string.loading)
        .setMessage(R.string.please_wait)
        .create();
  }

  @Override
  public void onDestroyView() {
    handler.removeMessages(LoadingHandler.MSG_DISMISS);
    super.onDestroyView();
  }

  private static class LoadingHandler extends Handler {
    private static final int MSG_DISMISS = 0;
    private final WeakReference<DialogFragment> ref;

    private LoadingHandler(DialogFragment fragment) {
      ref = new WeakReference<>(fragment);
    }

    @Override
    public void handleMessage(Message msg) {
      DialogFragment fragment = ref.get();
      if (fragment != null) {
        fragment.dismiss();
        Activity activity = fragment.getActivity();
        if (activity instanceof MainActivity) {
          ((MainActivity) activity).onLoadingFinished();
        }
      }
    }
  }
}