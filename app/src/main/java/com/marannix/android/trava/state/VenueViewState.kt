package com.marannix.android.trava.state

import com.marannix.android.trava.model.RecommendedPlacesModel

sealed class VenueViewState {
    object Loading : VenueViewState()
    data class Success(val venues: List<RecommendedPlacesModel>) : VenueViewState()
    data class ShowGenericError(val errorMessage: String?) : VenueViewState()
}
