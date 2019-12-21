package com.marannix.android.trava

import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var venueApi: VenueApi

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val disposable =  venueApi.getVenues("London").observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("lol", it.response.groups[0].items[0].venue.name)
            }, {
                Log.d("error", it.message)
            })

        compositeDisposable.add(disposable)
    }
}
