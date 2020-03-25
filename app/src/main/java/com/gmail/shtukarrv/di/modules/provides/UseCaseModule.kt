package com.gmail.shtukarrv.di.modules.provides

import com.gmail.shtukarrv.domain.repository.RatesRepository
import com.gmail.shtukarrv.domain.usecase.RatesUseCase
import com.gmail.shtukarrv.domain.usecase.RatesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesGetAuthUseCase(repository: RatesRepository): RatesUseCase = RatesUseCaseImpl(repository)
}