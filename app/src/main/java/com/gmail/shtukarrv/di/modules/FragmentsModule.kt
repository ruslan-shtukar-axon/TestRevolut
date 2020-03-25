package com.gmail.shtukarrv.di.modules

import com.gmail.shtukarrv.presentation.ui.rates.RatesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun getMainFragment(): RatesListFragment
}
