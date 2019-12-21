package com.marannix.android.trava.dagger.modules

import androidx.fragment.app.FragmentActivity
import com.marannix.android.trava.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainActivity(activity: MainActivity): FragmentActivity
}