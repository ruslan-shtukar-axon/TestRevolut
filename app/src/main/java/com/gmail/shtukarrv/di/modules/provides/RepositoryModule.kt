package com.gmail.shtukarrv.di.modules.provides

import com.gmail.shtukarrv.data.api.RatesApi
import com.gmail.shtukarrv.data.repository.RatesRepositoryImpl
import com.gmail.shtukarrv.domain.repository.RatesRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesAuthRepository(ratesApi: RatesApi): RatesRepository = RatesRepositoryImpl(ratesApi)

}