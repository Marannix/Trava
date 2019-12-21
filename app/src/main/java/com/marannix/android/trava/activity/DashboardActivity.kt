package com.marannix.android.trava.activity

import android.os.Bundle
import com.marannix.android.trava.R
import com.marannix.android.trava.fragment.DashboardFragment

class DashboardActivity : BaseActivity() {

//    @Inject
//    lateinit var venueApi: VenueApi
//
//    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDashboardFragment()
    }

    private fun initDashboardFragment() {
        val fragment = DashboardFragment.newInstance()
        supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment)
        }
    }

//    fun stuff() {
        //       val disposable =  venueApi.getVenues("London").observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                Log.d("lol", it.response.groups[0].items[0].venue.name)
//            }, {
//                Log.d("error", it.message)
//            })
//ss
//        compositeDisposable.add(disposable)
  //  }
}
