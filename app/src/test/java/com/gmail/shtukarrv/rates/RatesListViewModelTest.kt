package com.gmail.shtukarrv.rates

import com.gmail.shtukarrv.BaseTest
import com.gmail.shtukarrv.TEST_CODE_ERROR
import com.gmail.shtukarrv.TEST_MESSAGE_ERROR
import com.gmail.shtukarrv.domain.common.ResultState
import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import com.gmail.shtukarrv.domain.entity.ErrorState
import com.gmail.shtukarrv.domain.usecase.RatesUseCase
import com.gmail.shtukarrv.presentation.ui.rates.RatesListViewModel
import com.gmail.shtukarrv.presentation.ui.rates.RatesListViewModel.Companion.DEFAULT_CURRENCY
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.util.concurrent.TimeUnit

class RatesListViewModelTest : BaseTest() {

    @Mock
    lateinit var ratesUseCase: RatesUseCase

    @InjectMocks
    lateinit var viewModel: RatesListViewModel

    private lateinit var testScheduler: TestScheduler

    override fun setup() {
        super.setup()
        testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @Test
    fun `get currency rate`() {
        val resultSuccess: ResultState<List<CurrencyInfo>> = ResultState.Success(listOf())

        Mockito.`when`(ratesUseCase.getCurrencyRate(DEFAULT_CURRENCY))
                .thenReturn(Observable.just(resultSuccess))
        viewModel.getCurrencyRate(DEFAULT_CURRENCY)

        testScheduler.advanceTimeTo(1, TimeUnit.SECONDS)

        Assert.assertEquals(resultSuccess, viewModel.getCurrencyRateStatus().value)
    }

    @Test
    fun `get currency rate error`() {
        val resultError: ResultState<List<CurrencyInfo>> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), null)

        Mockito.`when`(ratesUseCase.getCurrencyRate(DEFAULT_CURRENCY))
                .thenReturn(Observable.just(resultError))
        viewModel.getCurrencyRate(DEFAULT_CURRENCY)

        testScheduler.advanceTimeTo(1, TimeUnit.SECONDS)

        Assert.assertEquals(resultError, viewModel.getCurrencyRateStatus().value)
    }
}