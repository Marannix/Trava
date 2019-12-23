package com.marannix.android.trava.activity

import android.os.Bundle
import android.util.Log
import com.marannix.android.trava.R
import com.marannix.android.trava.fragment.DashboardFragment
import com.marannix.android.trava.fragment.VenueFragment

class DashboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDashboardFragment()
    }

    private fun initDashboardFragment() {
        val fragment = DashboardFragment.newInstance()
        fragment.attach(object : DashboardFragment.OnCitySelectedListener {
            override fun onCitySelected() {
                initVenueFragment()
            }
        })
        supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment).addToBackStack(fragment.javaClass.name)
        }
    }

    private fun initVenueFragment() {
        val fragment = VenueFragment.newInstance()
        supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment).addToBackStack(fragment.javaClass.name)
        }
    }

}
