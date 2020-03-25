package com.gmail.shtukarrv.di.modules

import android.content.Context
import com.gmail.shtukarrv.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideApplicationContext(App: App): Context
}
