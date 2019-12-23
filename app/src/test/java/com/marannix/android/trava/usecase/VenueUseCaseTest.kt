package com.marannix.android.trava.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.marannix.android.trava.api.VenueApi
import com.marannix.android.trava.model.VenueResponse
import com.marannix.android.trava.repository.VenueRepository
import com.marannix.android.trava.state.VenueDataState
import com.marannix.android.trava.utils.RxImmediateSchedulerRule
import com.marannix.android.trava.utils.UnitTestUtils
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

private const val a_city = "Newcastle"
private const val an_error = "No network"

class VenueUseCaseTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val api = Mockito.mock(VenueApi::class.java)

    private val venueRepository by lazy { VenueRepository(api) }
    private val venueUseCase by lazy { VenueUseCase(venueRepository) }

    private lateinit var venueDataState: VenueDataState
    private lateinit var expectedVenueDataSuccessState: VenueDataState.Success
    private val expectedVenueDataErrorState =  VenueDataState.GenericError(an_error)

    private lateinit var venueResponse: VenueResponse

    @Before
    fun setUp() {
        retrieveJson()
        setExpectedStates()
    }

    @Test
    fun `when network succeed, emit venue data success state`() {
        Mockito.`when`(api.getVenues(a_city))
            .thenReturn(Single.just(venueResponse))

        venueUseCase.getVenueDataState(a_city).subscribe { dataState ->
            venueDataState = dataState
        }

        Assert.assertEquals(venueDataState, expectedVenueDataSuccessState)
    }

    @Test
    fun `when network fails, emit venue data failed state`() {
        Mockito.`when`(api.getVenues(a_city))
            .thenReturn(Single.error(Throwable(an_error)))

        venueUseCase.getVenueDataState(a_city).subscribe { dataState ->
            venueDataState = dataState
        }

        Assert.assertEquals(venueDataState, expectedVenueDataErrorState)
    }


    private fun retrieveJson() {
        val response = UnitTestUtils.readJsonFile("venue.json")
        venueResponse = GsonBuilder().create().fromJson(response, VenueResponse::class.java)
    }

    private fun setExpectedStates() {
        expectedVenueDataSuccessState = VenueDataState.Success(venueResponse.response.groups[0].items)
    }
}