package com.gmail.shtukarrv.presentation.ui.rates.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gmail.shtukarrv.domain.entity.CurrencyInfo

class CurrencyDiffUtilCallback(private val oldCurrencies: List<CurrencyInfo>,
                               private val newCurrencies: List<CurrencyInfo>) : DiffUtil.Callback() {

    companion object {
        const val UPDATE_RATE = 1
    }

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldCurrencies[oldPos].currencyCode == newCurrencies[newPos].currencyCode
    }

    override fun getOldListSize(): Int {
        return oldCurrencies.size
    }

    override fun getNewListSize(): Int {
        return newCurrencies.size
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        if (oldPos == 0 && newPos == 0) {
            return true
        }
        return (oldCurrencies[oldPos].currencyRate == newCurrencies[newPos].currencyRate)
    }

    override fun getChangePayload(oldPos: Int, newPos: Int): Any? {
        if (oldCurrencies[oldPos].currencyRate != newCurrencies[newPos].currencyRate) {
            return UPDATE_RATE
        }
        return null
    }
}