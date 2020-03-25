package com.gmail.shtukarrv.presentation.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.shtukarrv.domain.common.ResultState
import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import com.gmail.shtukarrv.domain.entity.ErrorState
import com.gmail.shtukarrv.domain.usecase.RatesUseCase
import com.gmail.shtukarrv.presentation.common.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * RatesListViewModel retrieves the information about the currency rates from the server and
 * returns it formatted to the view.
 */
class RatesListViewModel @Inject constructor(private val ratesUseCase: RatesUseCase) : BaseViewModel() {

    companion object {
        const val PERIOD = 1L
        const val DEFAULT_CURRENCY = "EUR"
    }

    private val currencyRateStatus by lazy { MutableLiveData<ResultState<List<CurrencyInfo>>>() }

    fun getCurrencyRateStatus(): LiveData<ResultState<List<CurrencyInfo>>> = currencyRateStatus

    init {
        getCurrencyRate(DEFAULT_CURRENCY)
    }

    /**
     * Get the whole information about the currency rate.
     */
    fun getCurrencyRate(currency: String?) {
        onCleared()
        Observable.interval(PERIOD, TimeUnit.SECONDS)
                .flatMap { ratesUseCase.getCurrencyRate(currency) }
                .subscribe({ resultState: ResultState<List<CurrencyInfo>> ->
                    currencyRateStatus.postValue(resultState)
                }, { t: Throwable ->
                    currencyRateStatus.postValue(ResultState.Error((ErrorState(0, t.message.toString())), null))
                }).track()

    }
}
