package com.marannix.android.trava.model

data class RecommendedVenueModel(
    val name: String,
    val location: VenueLocation,
    val categories: List<VenueCategory>
)
