package com.marannix.android.trava.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.marannix.android.trava.DashboardActivity
import dagger.Binds
import dagger.Module

@Module
abstract class DashboardActivityModule {

    @Binds
    abstract fun provideDashboardActivity(activity: DashboardActivity): FragmentActivity
}