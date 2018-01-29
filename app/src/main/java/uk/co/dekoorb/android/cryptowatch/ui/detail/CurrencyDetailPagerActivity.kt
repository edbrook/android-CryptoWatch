package uk.co.dekoorb.android.cryptowatch.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_currency_pager.*
import uk.co.dekoorb.android.cryptowatch.R

/**
 * Created by ed on 29/01/18.
 */
class CurrencyDetailPagerActivity: AppCompatActivity() {
    private var mViewModel: CurrencyPagerViewModel? = null
    private var mAdapter: CurrencyPagerAdapter? = null

    companion object {
        private const val KEY_CURRENCY_ID = "key::currency_id"
        private const val KEY_FILTER = "key::filter_extra"

        fun getIntent(context: Context, currencyId: String, filter: String): Intent {
            val intent = Intent(context, CurrencyDetailPagerActivity::class.java)
            intent.putExtra(KEY_CURRENCY_ID, currencyId)
            intent.putExtra(KEY_FILTER, filter)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_pager)

        mAdapter = CurrencyPagerAdapter(supportFragmentManager)

        val currencyId = intent.getStringExtra(KEY_CURRENCY_ID)
        val filter = intent.getStringExtra(KEY_FILTER)

        mViewModel = ViewModelProviders.of(this).get(CurrencyPagerViewModel::class.java)
        mViewModel!!.setFilter(filter)
        mViewModel!!.getCurrencyIdList().observe(this, Observer<List<String>> {
            if (it != null) {
                // TODO - find a better way of setting the initial ViewPager position
                if (mViewModel!!.getPosition() == -1) {
                    for (i in 0..it.size) {
                        if (it[i] == currencyId) {
                            mViewModel!!.setPosition(i)
                            break
                        }
                    }
                }
                mAdapter!!.setCurrencyIdList(it)
                currency_pager.setCurrentItem(mViewModel!!.getPosition(), false)
            }
        })

        currency_pager.adapter = mAdapter
        currency_pager.addOnPageChangeListener( object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mViewModel!!.setPosition(position)
            }
        })
    }
}