package uk.co.dekoorb.android.cryptowatch.ui.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.dekoorb.android.cryptowatch.R
import uk.co.dekoorb.android.cryptowatch.databinding.CurrencyListItemBinding
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency
import uk.co.dekoorb.android.cryptowatch.ui.ItemClickListener

/**
 * Created by edbrook on 22/01/2018.
 */
class CurrencyListAdapter(listener: ItemClickListener):
        RecyclerView.Adapter<CurrencyListAdapter.CurrencyViewHolder>() {

    private var mCurrencyList: List<Currency>? = null
    private val mItemClickListener: ItemClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CurrencyViewHolder {
        val itemBinding: CurrencyListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.currency_list_item,
                parent, false)

        return CurrencyViewHolder(itemBinding, mItemClickListener)
    }

    override fun getItemCount(): Int {
        return mCurrencyList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder?, position: Int) {
        holder?.setCurrency(mCurrencyList!![position])
    }

    fun setCurrencyList(currencyList: List<Currency>?) {
        mCurrencyList = currencyList
        notifyDataSetChanged()
    }

    class CurrencyViewHolder(itemBinding: CurrencyListItemBinding, listener: ItemClickListener):
            RecyclerView.ViewHolder(itemBinding.root),
            View.OnClickListener,
            View.OnLongClickListener {

        private val mListener = listener
        private val mBinding = itemBinding

        fun setCurrency(currency: Currency) {
            mBinding.currency = currency
        }

        override fun onClick(p0: View?) {
            mListener.onClick(mBinding.currency!!)
        }

        override fun onLongClick(p0: View?): Boolean {
            return mListener.onLongClick(mBinding.currency!!)
        }

        init {
            mBinding.currencyItem.setOnClickListener(this)
            mBinding.currencyItem.setOnLongClickListener(this)
        }
    }
}