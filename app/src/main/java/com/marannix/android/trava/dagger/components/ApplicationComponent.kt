package com.marannix.android.trava.dagger.components

import android.app.Application
import com.marannix.android.trava.MainApplication
import com.marannix.android.trava.dagger.modules.ActivityBuilder
import com.marannix.android.trava.dagger.modules.ApiModule
import com.marannix.android.trava.dagger.modules.ApplicationModule
import com.marannix.android.trava.dagger.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ActivityBuilder::class,
        ApiModule::class,
        ApplicationModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(application: MainApplication)
}