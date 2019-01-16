package com.sqisland.android.espresso.recycler_view_basic

import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.ArrayList

class NumberedAdapter(count: Int, private val listener: OnItemClickListener?) : RecyclerView.Adapter<TextViewHolder>() {
  private val labels: MutableList<String> =  ArrayList<String>(count).apply {
    for (i in 0 until count) {
      add(i.toString())
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(
      android.R.layout.simple_list_item_1, parent, false)
    return TextViewHolder(view)
  }

  override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
    val label = labels[position]
    holder.textView.text = label
    val outValue = TypedValue()
    holder.textView.context.theme.resolveAttribute(
      android.R.attr.selectableItemBackground, outValue, true)
    holder.textView.setBackgroundResource(outValue.resourceId)

    if (listener != null) {
      holder.textView.setOnClickListener { listener.onItemClick(position) }
    }
  }

  override fun getItemCount(): Int {
    return labels.size
  }

  interface OnItemClickListener {
    fun onItemClick(position: Int)
  }
}