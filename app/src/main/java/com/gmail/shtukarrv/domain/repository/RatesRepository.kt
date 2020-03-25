package com.gmail.shtukarrv.domain.repository

import com.gmail.shtukarrv.domain.common.ResultState
import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import io.reactivex.Observable

interface RatesRepository {
    fun getCurrencyRate(baseCurrency: String?): Observable<ResultState<List<CurrencyInfo>>>
}
