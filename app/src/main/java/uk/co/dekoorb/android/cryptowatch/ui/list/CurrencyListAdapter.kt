package uk.co.dekoorb.android.cryptowatch.ui.list

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import uk.co.dekoorb.android.cryptowatch.R
import uk.co.dekoorb.android.cryptowatch.databinding.CurrencyListItemBinding
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency
import uk.co.dekoorb.android.cryptowatch.ui.ItemClickListener
import uk.co.dekoorb.android.cryptowatch.utils.CurrencyAppUtils
import java.util.*

/**
 * Created by edbrook on 22/01/2018.
 */
class CurrencyListAdapter(context: Context, listener: ItemClickListener):
        RecyclerView.Adapter<CurrencyListAdapter.CurrencyViewHolder>() {

    var mContext: Context = context
    private var mCurrencyList: List<Currency>? = null
//    private var mCurrencyIconMap: HashMap<String, String>? = null
    private val mItemClickListener: ItemClickListener = listener

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CurrencyViewHolder {
        val itemBinding: CurrencyListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.currency_list_item,
                parent, false)

        return CurrencyViewHolder(itemBinding, mItemClickListener)
    }

    override fun getItemId(position: Int): Long {
        val s = mCurrencyList!![position].id
        return UUID.nameUUIDFromBytes(s.toByteArray()).mostSignificantBits
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

//    fun setCurrencyIconMap(map: HashMap<String, String>) {
//        mCurrencyIconMap = map
//    }

    inner class CurrencyViewHolder(itemBinding: CurrencyListItemBinding, listener: ItemClickListener):
            RecyclerView.ViewHolder(itemBinding.root),
            View.OnClickListener,
            View.OnLongClickListener {

        private val mListener = listener
        private val mBinding = itemBinding

        fun setCurrency(currency: Currency) {
            mBinding.currency = currency
            Picasso.with(mContext)
                    .load(CurrencyAppUtils.getIconUrlForId(currency.id))
                    .into(mBinding.imCurrencyLogo)
//            val iconUrl = mCurrencyIconMap?.get(currency.symbol)
//            if (iconUrl != null) {
//                Picasso.with(mContext).load(iconUrl).into(mBinding.imCurrencyLogo)
//            }
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