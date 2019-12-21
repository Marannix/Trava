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
        fun onCitySelected(city: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }

    fun attach(listener: OnCitySelectedListener) {
        this.listener = listener
    }

    private var city = emptyList<String>()
    private val adapter = CityAdapter()
    private var listener: OnCitySelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createList()
        autoCompleteTextView.setBackgroundResource(0)
        setAdapter()

        adapter.setCities(city)
        setupListeners()
        autoCompleteTextView.addTextChangedListener {
            adapter.filter.filter(it.toString())
        }
    }

    private fun createList() {
        city = resources.getStringArray(R.array.cities_array).toList()
    }

    private fun setAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }


    private fun setupListeners() {
        adapter.setListener(object: CityAdapter.OnCityAdapterSelectedListener {
            override fun onCitySelected(city: String) {
                listener?.onCitySelected(city)
            }
        })
    }
//    @Inject
//    lateinit var venueApi: VenueApi
//
//    private val compositeDisposable = CompositeDisposable()

//    fun stuff() {
//        val disposable = venueApi.getVenues("London").observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                Log.d("lol", it.response.groups[0].items[0].venue.name)
//            }, {
//                Log.d("error", it.message)
//            })
//
//        compositeDisposable.add(disposable)
//    }


}
