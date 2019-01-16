package com.sqisland.android.espresso.recycler_view_basic

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val textView: TextView = itemView as TextView
}