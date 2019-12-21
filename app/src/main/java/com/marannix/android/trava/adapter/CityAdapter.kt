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

// https://www.androidhive.info/RxJava/android-rxjava-instant-search-local-remote-databases/
// https://www.youtube.com/watch?v=ocM1Yw_ktqM&t=4s
class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>(), Filterable {

    interface OnCityAdapterSelectedListener {
        fun onCitySelected(city: String)
    }

    fun setListener(listener: OnCityAdapterSelectedListener) {
        this.listener = listener
    }

    private var cities = emptyList<String>()
    private var cityListFiltered = emptyList<String>()
    private var listener: OnCityAdapterSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cities_auto_complete_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cityListFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (cityListFiltered.isNotEmpty()) {
            holder.bind(cityListFiltered[position], listener)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                cityListFiltered = if (charString.isEmpty()) {
                    //TODO: Show items which have been selected previously
                    emptyList()
                } else {
                    val filteredList = ArrayList<String>()
                    for (row in cities) {
                        if (row.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cities: String, listener: OnCityAdapterSelectedListener?) {
            itemView.cityName.text = cities
            itemView.setOnClickListener {
                listener?.onCitySelected(cities)
                Log.d("lol", cities)
            }
        }
    }

    fun clearListener() {
        listener = null
    }
}