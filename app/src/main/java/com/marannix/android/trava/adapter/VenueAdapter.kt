package com.marannix.android.trava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marannix.android.trava.R
import com.marannix.android.trava.model.RecommendedPlacesModel
import kotlinx.android.synthetic.main.venue_item_row.view.*

class VenueAdapter : RecyclerView.Adapter<VenueAdapter.ViewHolder>() {

    private var venues: List<RecommendedPlacesModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.venue_item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return venues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (venues.isNotEmpty()) {
            holder.bind(venues[position])
        }
    }

    fun setVenues(venues: List<RecommendedPlacesModel>) {
        this.venues = venues
        this.notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(venues: RecommendedPlacesModel) {
            itemView.venueName.text = venues.venue.name
        }
    }

}