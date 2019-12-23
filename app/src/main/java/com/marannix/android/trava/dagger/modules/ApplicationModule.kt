package com.marannix.android.trava.dagger.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    internal fun provideContext(application: Application): Context {
        return application.baseContext
    }

}