package com.gmail.shtukarrv.presentation.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.shtukarrv.R
import com.gmail.shtukarrv.domain.common.ResultState
import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import com.gmail.shtukarrv.presentation.extentions.hide
import com.gmail.shtukarrv.presentation.extentions.observe
import com.gmail.shtukarrv.presentation.extentions.show
import com.gmail.shtukarrv.presentation.ui.rates.adapter.CurrencyAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_rate_list.*
import javax.inject.Inject

class RatesListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RatesListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RatesListViewModel::class.java)
    }

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rate_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currencyAdapter = CurrencyAdapter {
            rvRatesList.scrollToPosition(0)
            viewModel.getCurrencyRate(it)
        }
        layoutManager = LinearLayoutManager(context)
        rvRatesList.layoutManager = layoutManager
        rvRatesList.adapter = currencyAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(viewModel.getCurrencyRateStatus(), ::onCurrencyRate)
    }

    private fun onCurrencyRate(result: ResultState<List<CurrencyInfo>>) {
        pbLoading.hide()
        when (result) {
            is ResultState.Success -> {
                rvRatesList.show()
                tvEmptyList.hide()
                currencyAdapter.setCurrenciesList(result.data)
            }
            is ResultState.Loading -> {
                rvRatesList.hide()
                tvEmptyList.show()
            }
            is ResultState.Error ->
                Toast.makeText(activity, getString(R.string.default_error), Toast.LENGTH_SHORT).show()
        }
    }
}
