package uk.co.dekoorb.android.cryptowatch.ui.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import uk.co.dekoorb.android.cryptowatch.R
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency
import uk.co.dekoorb.android.cryptowatch.ui.detail.CurrencyDetailActivity

class CurrencyListActivity : AppCompatActivity(),
        CurrencyListFragment.Companion.CurrencyListFragmentActions {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)

        if (savedInstanceState == null) {
            val fragment = CurrencyListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .add(R.id.currency_list_frame, fragment, CurrencyListFragment.TAG)
                    .commit()
        }
    }

    override fun onCurrencyItemClick(currency: Currency) {
        val intent = CurrencyDetailActivity.getIntent(this, currency.id)
        startActivity(intent)
    }

    override fun onCurrencyItemLongClick(currency: Currency): Boolean {
        return false
    }
}
