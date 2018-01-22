package uk.co.dekoorb.android.cryptowatch.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.dekoorb.android.cryptowatch.R
import uk.co.dekoorb.android.cryptowatch.databinding.FragmentCurrencyListBinding
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency
import uk.co.dekoorb.android.cryptowatch.ui.ItemClickListener

/**
 * Created by edbrook on 22/01/2018.
 */
class CurrencyListFragment:
        Fragment(), ItemClickListener {

    companion object {
        const val TAG = "CurrencyListFragment"
        fun newInstance(): CurrencyListFragment {
            return CurrencyListFragment()
        }
    }

    private var mAdapter: CurrencyListAdapter? = null
    private var mBinding: FragmentCurrencyListBinding? = null
    private var mViewModel: CurrencyListViewModel? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mAdapter = CurrencyListAdapter(this)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency_list, container, false)
        mBinding!!.currencyList.layoutManager = LinearLayoutManager(context)
        mBinding!!.currencyList.adapter = mAdapter
        return mBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(CurrencyListViewModel::class.java)
        subscribeUi()
    }

    private fun subscribeUi() {
        mViewModel?.getCurrencyList()?.observe(this, Observer<List<Currency>> {
            mAdapter?.setCurrencyList(it)
        })
    }

    override fun onClick(currency: Currency) {
        Log.d(TAG, "onClick: " + currency.name)
    }

    override fun onLongClick(currency: Currency): Boolean {
        Log.d(TAG, "onLongClick: " + currency.name)
        return true
    }
}