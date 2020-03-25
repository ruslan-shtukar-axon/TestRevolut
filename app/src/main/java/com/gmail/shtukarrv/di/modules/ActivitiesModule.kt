package com.gmail.shtukarrv.di.modules

import com.gmail.shtukarrv.presentation.ui.MainActivity
import com.gmail.shtukarrv.presentation.ui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun getSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun getMainActivity(): MainActivity
}
