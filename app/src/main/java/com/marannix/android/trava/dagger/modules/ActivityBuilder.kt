package com.marannix.android.trava.dagger.modules

import com.marannix.android.trava.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeLoginActivity(): MainActivity
}