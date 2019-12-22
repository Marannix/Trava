package com.marannix.android.trava.repository

import com.marannix.android.trava.RecommendedPlacesModel
import com.marannix.android.trava.api.VenueApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VenueRepository @Inject constructor(
    private val venueApi: VenueApi
) {

    fun fetchVenues(city: String): Observable<List<RecommendedPlacesModel>> {
        return fetchVenueFromApi(city).toObservable()
    }

    private fun fetchVenueFromApi(city: String): Single<List<RecommendedPlacesModel>> {
        return venueApi.getVenues(city).map {
            it.response.groups[0].items
        }.subscribeOn(Schedulers.io())
    }
}