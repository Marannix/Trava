package com.marannix.android.trava.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.marannix.android.trava.repository.VenueRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VenueViewModel @Inject constructor(
    private val venueRepository: VenueRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getRecommendedVenues(city: String) {
        val disposable = venueRepository.fetchVenueFromApi(city).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("oiuch", it[0].venue.name)
            }, {
                Log.d("fail", it.message)
            })

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}