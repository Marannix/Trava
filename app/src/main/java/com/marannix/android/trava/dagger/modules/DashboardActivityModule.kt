package com.marannix.android.trava.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.marannix.android.trava.activity.DashboardActivity
import com.marannix.android.trava.fragment.DashboardFragment
import com.marannix.android.trava.fragment.VenueFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Modules for Dashboard activity and related fragments
 */
@Module
abstract class DashboardActivityModule {

    @Binds
    abstract fun provideDashboardActivity(activity: DashboardActivity): FragmentActivity

    @ContributesAndroidInjector
    abstract fun dashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun venueFragment(): VenueFragment

}