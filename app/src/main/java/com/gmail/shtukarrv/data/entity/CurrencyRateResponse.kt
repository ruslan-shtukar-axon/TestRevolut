package com.gmail.shtukarrv.data.entity

import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import com.google.gson.annotations.SerializedName

data class CurrencyRateResponse(
        @SerializedName("baseCurrency") val baseCurrency: String,
        @SerializedName("rates") var ratesList: Map<String, Float>) {

    fun toCurrencyList(): List<CurrencyInfo> {
        val currencies: MutableList<CurrencyInfo> = mutableListOf()
        ratesList.keys.forEach { currencies.add(CurrencyInfo(it, ratesList[it] ?: 0f)) }
        currencies.add(0, CurrencyInfo(baseCurrency, 1f))
        return currencies
    }
}