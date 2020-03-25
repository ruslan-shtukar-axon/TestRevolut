package com.gmail.shtukarrv.data.api

import com.gmail.shtukarrv.data.ApiConstants.CURRENCY_RATES_PATH
import com.gmail.shtukarrv.data.entity.CurrencyRateResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of base currency webservices
 */
interface RatesApi {

    @GET(CURRENCY_RATES_PATH)
    fun getCurrencyRate(@Query("base") status: String?): Observable<CurrencyRateResponse>

}
