package com.sqisland.android.espresso.recycler_view_basic

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val textView: TextView = itemView as TextView
}