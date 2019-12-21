package com.marannix.android.trava.api

import com.marannix.android.trava.VenueResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface VenueApi {
    @GET("venues/explore")
    fun getVenues(
        @Query("near") city: String
    ) : Single<VenueResponse>
}