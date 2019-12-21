package com.marannix.android.trava.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marannix.android.trava.R
import com.marannix.android.trava.repository.VenueRepository
import io.reactivex.android.schedulers.AndroidSchedulers
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
    }

    private fun updateToolbar() {
        toolbar.title = city
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun init() {
        city?.let {
            repository.fetchVenueFromApi(it).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("oiuch", it[0].venue.name)
                }, {
                    Log.d("fail", it.message)
                })
        }
    }
}
