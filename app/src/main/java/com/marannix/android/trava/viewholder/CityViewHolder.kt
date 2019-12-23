package com.marannix.android.trava.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.marannix.android.trava.adapter.CityAdapter
import kotlinx.android.synthetic.main.cities_auto_complete_row.view.*

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(cities: String, listener: CityAdapter.OnCityAdapterSelectedListener?) {
        itemView.cityName.text = cities
        itemView.setOnClickListener {
            listener?.onCitySelected(cities)
        }
    }
}
