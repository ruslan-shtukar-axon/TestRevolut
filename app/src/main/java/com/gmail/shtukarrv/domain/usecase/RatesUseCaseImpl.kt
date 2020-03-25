package com.gmail.shtukarrv.domain.usecase

import com.gmail.shtukarrv.domain.common.ResultState
import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import com.gmail.shtukarrv.domain.repository.RatesRepository
import io.reactivex.Observable

class RatesUseCaseImpl(private val repository: RatesRepository) : RatesUseCase {

    override fun getCurrencyRate(currency: String?): Observable<ResultState<List<CurrencyInfo>>> =
            repository.getCurrencyRate(currency)
}
