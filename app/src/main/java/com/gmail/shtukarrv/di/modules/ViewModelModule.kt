package com.gmail.shtukarrv.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.shtukarrv.di.qualifier.ViewModelKey
import com.gmail.shtukarrv.presentation.ui.rates.RatesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RatesListViewModel::class)
    internal abstract fun bindRatesListViewModel(viewModelStep: RatesListViewModel): ViewModel
}
