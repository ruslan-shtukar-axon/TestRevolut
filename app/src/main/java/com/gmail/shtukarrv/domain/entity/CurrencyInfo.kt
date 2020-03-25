package com.gmail.shtukarrv.domain.entity

/**
 * A mapping from CurrencyRateResponse class. The set of params supported by this class
 * is purposefully restricted to the ones that can be used on UI.
 * @property currencyCode  the currency code in ISO format
 * @property currencyRate the rate of the currency
 */
data class CurrencyInfo(val currencyCode: String? = null,
                        var currencyRate: Float? = null)



