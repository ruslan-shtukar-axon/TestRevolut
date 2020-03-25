package com.gmail.shtukarrv.presentation.ui.rates.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmail.shtukarrv.R
import com.gmail.shtukarrv.domain.entity.CurrencyInfo
import com.gmail.shtukarrv.presentation.extentions.loadFlagIcon
import kotlinx.android.synthetic.main.item_rate.view.*
import java.text.DecimalFormat
import java.util.*

class CurrencyAdapter(private val currencyClickListener: (String) -> Unit) :
        RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    companion object {
        const val RATE_DECIMAL_FORMAT = "#.##"
    }

    private var currencies: MutableList<CurrencyInfo> = mutableListOf()
    private var baseRate = 1f

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.isNullOrEmpty()) {
                changeBaseRate(0f)
            } else {
                changeBaseRate(s.toString().toFloat())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    fun setCurrenciesList(list: List<CurrencyInfo>) {
        val newCurrencies = list.sortedBy { currency ->
            currencies.map { it.currencyCode }.indexOf(currency.currencyCode)
        }

        val diffResult = DiffUtil.calculateDiff(
                CurrencyDiffUtilCallback(ArrayList(currencies), ArrayList(newCurrencies)))

        if (currencies.isNotEmpty() && newCurrencies.isNotEmpty() &&
                currencies[0].currencyCode == newCurrencies[0].currencyCode) {
            val baseCurrency = currencies[0]
            currencies.clear()
            if (newCurrencies.size > 1) {
                currencies.addAll(newCurrencies.subList(1, newCurrencies.size))
            }
            currencies.add(0, baseCurrency)
        } else {
            currencies.clear()
            currencies.addAll(newCurrencies)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    fun changeBaseCurrency(position: Int) {
        val newBaseCurrency = currencies.removeAt(position)
        baseRate = newBaseCurrency.currencyRate?.times(baseRate) ?: baseRate
        newBaseCurrency.currencyRate = 1f
        currencies.add(0, newBaseCurrency)
        notifyItemMoved(position, 0)
        notifyItemChanged(0)
    }

    fun changeBaseRate(newRate: Float) {
        baseRate = newRate
        notifyItemRangeChanged(1, currencies.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencies[position], position)
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currencyInfo: CurrencyInfo, position: Int) {
            itemView.apply {
                if (position != 0) {
                    etCurrencyRate?.removeTextChangedListener(textWatcher)
                    setOnClickListener {
                        currencyInfo.currencyCode?.let {
                            currencyClickListener.invoke(it)
                            changeBaseCurrency(position)
                        }
                    }
                }

                ivIconFlag?.loadFlagIcon(currencyInfo.currencyCode)
                tvCurrencyCode?.text = currencyInfo.currencyCode
                tvCurrencyName?.text = Currency.getInstance(currencyInfo.currencyCode).displayName
                val rateText = DecimalFormat(RATE_DECIMAL_FORMAT)
                        .format(currencyInfo.currencyRate?.times(baseRate))
                etCurrencyRate?.setText(rateText)

                if (position == 0) {
                    etCurrencyRate?.addTextChangedListener(textWatcher)
                    etCurrencyRate.requestFocus()
                    setOnClickListener(null)
                }
            }
        }
    }
}