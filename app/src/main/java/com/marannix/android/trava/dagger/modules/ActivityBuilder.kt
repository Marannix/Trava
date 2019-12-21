package com.marannix.android.trava.dagger.modules

import com.marannix.android.trava.DashboardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [DashboardActivityModule::class])
    abstract fun contributeLoginActivity(): DashboardActivity
}