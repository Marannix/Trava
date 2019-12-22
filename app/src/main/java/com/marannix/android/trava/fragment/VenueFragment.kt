package com.marannix.android.trava.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marannix.android.trava.R
import com.marannix.android.trava.adapter.VenueAdapter
import com.marannix.android.trava.repository.VenueRepository
import com.marannix.android.trava.state.VenueViewState
import com.marannix.android.trava.viewmodel.VenueViewModel
import kotlinx.android.synthetic.main.fragment_venue.*
import javax.inject.Inject

private const val ARG_CITY = "city"

class VenueFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance(city: String) = VenueFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CITY, city)
            }
        }
    }

    @Inject
    lateinit var repository: VenueRepository
    private var city: String? = null
    private var viewmodel: VenueViewModel? = null
    private val adapter by lazy { VenueAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(ARG_CITY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_venue, container, false)
    }

    override fun onStart() {
        super.onStart()
        updateToolbar()
        init()
        getVenues()
        subscribeToVenueViewState()
        setAdapter()
    }

    private fun updateToolbar() {
        toolbar.title = city
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun init() {
        viewmodel =
            activity?.let { ViewModelProviders.of(it, viewModelFactory).get(VenueViewModel::class.java) }
    }

    private fun getVenues() {
        //TODO: City will never be null so why even make it null
        viewmodel!!.getRecommendedVenues(city!!)
    }

    private fun subscribeToVenueViewState() {
        viewmodel!!.venueViewState.observe(this, Observer { venueViewState ->
            when (venueViewState) {
                VenueViewState.Loading -> {
                    Log.d("Venue", "Loading")
                }
                is VenueViewState.Success -> {
                    adapter.setVenues(venueViewState.venues)
//                    Log.d("Venue", venueViewState.venues[0].venue.name)
                }
                is VenueViewState.ShowGenericError -> {
                    Log.d("Venue", venueViewState.errorMessage)
                }
            }
        })
    }

    private fun setAdapter() {
        venueRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        venueRecyclerView.adapter = adapter
    }

}
