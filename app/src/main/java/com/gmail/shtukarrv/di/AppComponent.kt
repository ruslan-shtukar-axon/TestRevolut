package com.gmail.shtukarrv.di

import com.gmail.shtukarrv.App
import com.gmail.shtukarrv.di.modules.*
import com.gmail.shtukarrv.di.modules.provides.RepositoryModule
import com.gmail.shtukarrv.di.modules.provides.UseCaseModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    FragmentsModule::class,
    NetworkModule::class,
    ActivitiesModule::class,
    AppModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])

interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}