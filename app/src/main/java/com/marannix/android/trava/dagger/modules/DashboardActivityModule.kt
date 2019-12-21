package com.marannix.android.trava.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.marannix.android.trava.activity.DashboardActivity
import com.marannix.android.trava.fragment.DashboardFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardActivityModule {

    @Binds
    abstract fun provideDashboardActivity(activity: DashboardActivity): FragmentActivity

    @ContributesAndroidInjector
    abstract fun dashboardFragment(): DashboardFragment

}