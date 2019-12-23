package com.marannix.android.trava.usecase

import com.marannix.android.trava.repository.VenueRepository
import com.marannix.android.trava.state.VenueDataState
import io.reactivex.Observable
import javax.inject.Inject

class VenueUseCase @Inject constructor(
    private val venueRepository: VenueRepository
) {

    /**
     * Return a data state to the viewmodel which will contain either a success or an error state
     */
    fun getVenueDataState(city: String): Observable<VenueDataState> {
        return venueRepository.fetchVenues(city).map<VenueDataState> { venues ->
            VenueDataState.Success(venues)
        }.onErrorReturn {
            VenueDataState.GenericError(it.message)
        }
    }
}