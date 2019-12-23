package com.marannix.android.trava.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marannix.android.trava.viewmodel.VenueViewModel
import com.marannix.android.trava.viewmodel.ViewModelFactory
import com.marannix.android.trava.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Modules for View Model
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(VenueViewModel::class)
    internal abstract fun bindingVenueModel(viewModel: VenueViewModel): ViewModel

}