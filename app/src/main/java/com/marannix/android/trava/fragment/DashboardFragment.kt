package com.marannix.android.trava.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.marannix.android.trava.R
import com.marannix.android.trava.adapter.CityAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : BaseFragment() {

    interface OnCitySelectedListener {
        fun onCitySelected()
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }

    fun attach(listener: OnCitySelectedListener) {
        this.listener = listener
    }

    private var city = emptyList<String>()
    private var topCity = emptyList<String>()
    private val adapter = CityAdapter()
    private var listener: OnCitySelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createList()
        setAdapter()
        setupListeners()
        setupAutoCompleteTextView()
    }

    /**
     * Create a fake list of cities and top cities
     */
    private fun createList() {
        city = resources.getStringArray(R.array.cities_array).toList()
        topCity = resources.getStringArray(R.array.top_cities_array).toList()
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        adapter.setCities(city)
        adapter.setTopCities(topCity)
    }

    private fun setupListeners() {
        adapter.setListener(object : CityAdapter.OnCityAdapterSelectedListener {
            override fun onCitySelected(city: String) {
                userPreference.setSelectedCity(city)
                closeKeyboard()
                listener?.onCitySelected()
            }
        })
    }

    /**
     * Remove auto complete text view background and set a filter listener
     */
    private fun setupAutoCompleteTextView() {
        autoCompleteTextView.setBackgroundResource(0)
        autoCompleteTextView.addTextChangedListener {
            adapter.filter.filter(it.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
        adapter.clearListener()
    }
}
