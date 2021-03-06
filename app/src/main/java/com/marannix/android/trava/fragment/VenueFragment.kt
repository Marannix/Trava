package com.marannix.android.trava.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marannix.android.trava.R
import com.marannix.android.trava.adapter.VenueAdapter
import com.marannix.android.trava.dialog.FullscreenLoadingDialog
import com.marannix.android.trava.repository.VenueRepository
import com.marannix.android.trava.state.VenueViewState
import com.marannix.android.trava.viewmodel.VenueViewModel
import kotlinx.android.synthetic.main.auth_error_layout.*
import kotlinx.android.synthetic.main.fragment_venue.*
import javax.inject.Inject

class VenueFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = VenueFragment()
    }

    @Inject
    lateinit var repository: VenueRepository
    private var viewmodel: VenueViewModel? = null
    private val adapter by lazy { VenueAdapter() }
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = FullscreenLoadingDialog(requireContext()).apply {
            setCanceledOnTouchOutside(false)
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
        setAdapter()
        subscribeToVenueViewState()
    }

    private fun updateToolbar() {
        toolbar.title = userPreference.getSelectedCity()
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun init() {
        viewmodel =
            activity?.let { ViewModelProviders.of(it, viewModelFactory).get(VenueViewModel::class.java) }
    }

    private fun getVenues() {
        viewmodel!!.getRecommendedVenues(userPreference.getSelectedCity())
    }

    /**
     * The UI is updated based on the view state emitted from the viewmodel
     */
    private fun subscribeToVenueViewState() {
        viewmodel!!.venueViewState.observe(this, Observer { venueViewState ->
            when (venueViewState) {
                VenueViewState.Loading -> {
                    loadingDialog.show()
                }
                is VenueViewState.Success -> {
                    loadingDialog.dismiss()
                    showLayout()
                    recommendedVenueLabel.text = getString(R.string.recommended_places)
                    adapter.setVenues(venueViewState.venues)
                }
                is VenueViewState.ShowGenericError -> {
                    loadingDialog.dismiss()
                    showErrorLayout()
                }
            }
        })
    }

    private fun setAdapter() {
        venueRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        venueRecyclerView.adapter = adapter
        venueRecyclerView.isNestedScrollingEnabled = false
    }

    /**
     * Added a restart button to fetch venue
     */
    private fun showErrorLayout() {
        venueRecyclerView.visibility = View.INVISIBLE
        errorAuthLayout.visibility = View.VISIBLE

        restartButton.setOnClickListener {
            getVenues()
        }
    }

    private fun showLayout() {
        venueRecyclerView.visibility = View.VISIBLE
        errorAuthLayout.visibility = View.INVISIBLE
    }
}
