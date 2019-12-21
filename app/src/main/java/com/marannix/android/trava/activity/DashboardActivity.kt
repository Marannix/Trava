package com.marannix.android.trava.activity

import android.os.Bundle
import com.marannix.android.trava.R
import com.marannix.android.trava.fragment.DashboardFragment

class DashboardActivity : BaseActivity() {

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

}
