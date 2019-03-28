package org.twodee.lately

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HeadlineAdapter(private val context: Context,
                      private val headlines: List<Headline>,
                      private val clickListener: (Headline) -> Unit): RecyclerView.Adapter<HeadlineViewHolder>() {

  private var selectedIndex = RecyclerView.NO_POSITION

  override fun getItemCount(): Int = headlines.size

  override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HeadlineViewHolder {
    val inflater = LayoutInflater.from(context)
    val view = inflater.inflate(R.layout.headline_item, parent, false)
    val holder = HeadlineViewHolder(view)

    view.setOnClickListener {
      clickListener(headlines[holder.adapterPosition])

      val oldSelectedIndex = selectedIndex
      selectedIndex = holder.adapterPosition
      notifyItemChanged(oldSelectedIndex)
      notifyItemChanged(selectedIndex)
    }

    return holder
  }

  override fun onBindViewHolder(holder: HeadlineViewHolder, i: Int) {
    holder.headlineText.text = headlines[i].text
    holder.isActive = selectedIndex == i
  }
}

class HeadlineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val headlineText: TextView = view.findViewById(R.id.headlineText)

  var isActive: Boolean = false
    set(value) {
      field = value
      itemView.setBackgroundColor(if (field) Color.LTGRAY else Color.TRANSPARENT)
    }
}
