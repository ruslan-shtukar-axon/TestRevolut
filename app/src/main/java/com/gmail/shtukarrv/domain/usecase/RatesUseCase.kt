package com.gmail.shtukarrv.domain.usecase

import com.gmail.shtukarrv.domain.common.ResultState
import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import io.reactivex.Observable

interface RatesUseCase {
    fun getCurrencyRate(currency: String?): Observable<ResultState<List<CurrencyInfo>>>
}
