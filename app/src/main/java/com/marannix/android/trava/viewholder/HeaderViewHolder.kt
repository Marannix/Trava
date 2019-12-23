package com.marannix.android.trava.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cities_header.view.*

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(filtered: Boolean) {
        when {
            filtered -> {
                itemView.topCityLabel.visibility = View.VISIBLE
                itemView.topCityLabel.text = "Top Cities"
            }
            else -> itemView.topCityLabel.visibility = View.GONE
        }
    }
}
