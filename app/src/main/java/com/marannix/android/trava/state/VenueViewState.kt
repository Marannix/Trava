package com.marannix.android.trava.state

import com.marannix.android.trava.model.RecommendedPlacesModel

/**
 * Default state is Loading
 * View state is updated based on data success and error state
 */
sealed class VenueViewState {
    object Loading : VenueViewState()
    data class Success(val venues: List<RecommendedPlacesModel>) : VenueViewState()
    data class ShowGenericError(val errorMessage: String?) : VenueViewState()
}
