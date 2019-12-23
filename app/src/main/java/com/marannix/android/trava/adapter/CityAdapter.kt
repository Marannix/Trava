package com.marannix.android.trava.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.marannix.android.trava.R
import kotlinx.android.synthetic.main.cities_auto_complete_row.view.*
import kotlinx.android.synthetic.main.cities_header.view.*

// https://www.androidhive.info/RxJava/android-rxjava-instant-search-local-remote-databases/
// https://www.youtube.com/watch?v=ocM1Yw_ktqM&t=4s
private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1

class CityAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    interface OnCityAdapterSelectedListener {
        fun onCitySelected(city: String)
    }

    fun setListener(listener: OnCityAdapterSelectedListener) {
        this.listener = listener
    }

    private var cities = emptyList<String>()
    private var topCities = emptyList<String>()
    private var cityListFiltered = emptyList<String>()
    private var listener: OnCityAdapterSelectedListener? = null
    private var shouldShowHeader = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_HEADER -> {
                return HeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.cities_header,
                        parent,
                        false
                    )
                )
            }
            TYPE_ITEM -> {
                return CityViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.cities_auto_complete_row,
                        parent,
                        false
                    )
                )
            }
            else -> {
                throw RuntimeException("No match for " + viewType + ".")
            }
        }
    }

    override fun getItemCount(): Int {
        return when {
            cityListFiltered.isEmpty() && shouldShowHeader -> topCities.size
            else -> cityListFiltered.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.bind(shouldShowHeader)
            }
            is CityViewHolder -> {
                when {
                    cityListFiltered.isNotEmpty() -> holder.bind(cityListFiltered[position], listener)
                    else -> if (shouldShowHeader) holder.bind(topCities[position], listener)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                TYPE_HEADER
            }
            else -> {
                TYPE_ITEM
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                shouldShowHeader = true
                cityListFiltered = when {
                    charString.isEmpty() -> topCities
                    else -> {
                        shouldShowHeader = false
                        val filteredList = ArrayList<String>()
                        for (row in cities) {
                            if (row.toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row)
                            }
                        }
                        filteredList
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = cityListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                cityListFiltered = filterResults?.values as List<String>
                notifyDataSetChanged()
            }
        }
    }

    fun setCities(cities: List<String>) {
        this.cities = cities
        this.notifyDataSetChanged()
    }

    fun setTopCities(topCities: List<String>) {
        this.topCities = topCities
        this.notifyDataSetChanged()
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cities: String, listener: OnCityAdapterSelectedListener?) {
            itemView.cityName.text = cities
            itemView.setOnClickListener {
                listener?.onCitySelected(cities)
                Log.d("lol", cities)
            }
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
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

    fun clearListener() {
        listener = null
    }
}