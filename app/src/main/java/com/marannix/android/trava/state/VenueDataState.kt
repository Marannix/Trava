package com.marannix.android.trava.state

import com.marannix.android.trava.model.RecommendedPlacesModel

sealed class VenueDataState {
    data class Success(val venues: List<RecommendedPlacesModel>) : VenueDataState()
    data class GenericError(val errorMessage: String?) : VenueDataState()
}