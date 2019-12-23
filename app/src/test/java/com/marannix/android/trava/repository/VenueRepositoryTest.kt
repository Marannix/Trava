package com.marannix.android.trava.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.marannix.android.trava.api.VenueApi
import com.marannix.android.trava.model.RecommendedPlacesModel
import com.marannix.android.trava.model.VenueResponse
import com.marannix.android.trava.utils.RxImmediateSchedulerRule
import com.marannix.android.trava.utils.UnitTestUtils.Companion.readJsonFile
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

private const val a_city = "Newcastle"

class VenueRepositoryTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val api = Mockito.mock(VenueApi::class.java)

    private val venueRepository by lazy { VenueRepository(api) }

    private lateinit var venueResponse: VenueResponse
    private var result = emptyList<RecommendedPlacesModel>()

    @Before
    fun setUp() {
        val response = readJsonFile("venue.json")
        venueResponse = GsonBuilder().create().fromJson(response, VenueResponse::class.java)
    }

    @Test
    fun `when calling the network, parse correctly the response and retrieve venues`() {
        Mockito.`when`(api.getVenues(a_city)).thenReturn(Single.just(venueResponse))

        venueRepository.fetchVenues(a_city)
            .blockingSubscribe {
                result = it!!
            }

        Assertions.assertThat(result).isEqualTo(venueResponse.response.groups[0].items)
    }
}