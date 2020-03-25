package com.gmail.shtukarrv.data.repository

import com.gmail.shtukarrv.data.api.RatesApi
import com.gmail.shtukarrv.data.common.applyIoScheduler
import com.gmail.shtukarrv.data.common.mapDefaultErrors
import com.gmail.shtukarrv.domain.common.ResultState
import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import com.gmail.shtukarrv.domain.repository.RatesRepository
import io.reactivex.Observable

class RatesRepositoryImpl(private val apiService: RatesApi) : RatesRepository {

    override fun getCurrencyRate(baseCurrency: String?): Observable<ResultState<List<CurrencyInfo>>> {
        return apiService.getCurrencyRate(baseCurrency)
                .applyIoScheduler()
                .map {
                    if (it.ratesList.isNotEmpty()) {
                        ResultState.Success(it.toCurrencyList())
                    } else {
                        ResultState.Loading(emptyList<CurrencyInfo>())
                    }
                }
                .mapDefaultErrors()
    }
}