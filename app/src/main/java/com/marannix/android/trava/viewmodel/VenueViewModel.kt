package com.marannix.android.trava.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marannix.android.trava.state.VenueDataState
import com.marannix.android.trava.state.VenueViewState
import com.marannix.android.trava.usecase.VenueUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VenueViewModel @Inject constructor(
    private val venueUseCase: VenueUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()
    val venueViewState = MutableLiveData<VenueViewState>()

    fun getRecommendedVenues(city: String) {
        val disposable = venueUseCase.getVenueDataState(city)
            .observeOn(AndroidSchedulers.mainThread())
            .map { venueDataState ->
                return@map when (venueDataState) {
                    is VenueDataState.Success -> {
                        VenueViewState.Success(venueDataState.venues)
                    }
                    is VenueDataState.GenericError -> {
                        VenueViewState.ShowGenericError(venueDataState.errorMessage)
                    }
                }
            }.doOnSubscribe { venueViewState.value = VenueViewState.Loading }
            .subscribe { viewstate ->
                venueViewState.value = viewstate
            }
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}