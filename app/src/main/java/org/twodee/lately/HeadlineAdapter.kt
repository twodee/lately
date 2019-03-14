package org.twodee.lately

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HeadlineAdapter(val context: Context,
                      val headlines: List<Headline>,
                      val clickListener: (Headline) -> Unit): RecyclerView.Adapter<HeadlineViewHolder>() {

  private var selectedIndex = RecyclerView.NO_POSITION

  override fun getItemCount(): Int = headlines.size

  override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HeadlineViewHolder {
    val inflater = LayoutInflater.from(context)
    val view = inflater.inflate(R.layout.headline_item, parent, false)
    return HeadlineViewHolder(view)
  }

  override fun onBindViewHolder(holder: HeadlineViewHolder, i: Int) {
    holder.headlineText.text = headlines[i].text
    holder.itemView.setOnClickListener {
      val oldSelectedIndex = selectedIndex
      selectedIndex = i
      clickListener(headlines[i])
      notifyItemChanged(oldSelectedIndex)
      notifyItemChanged(selectedIndex)
    }
    holder.itemView.setBackgroundColor(if (selectedIndex == i) Color.LTGRAY else Color.TRANSPARENT)
  }
}

class HeadlineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val headlineText: TextView = view.findViewById(R.id.headlineText)
}
