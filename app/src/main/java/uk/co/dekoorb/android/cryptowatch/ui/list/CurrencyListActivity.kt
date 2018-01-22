package uk.co.dekoorb.android.cryptowatch.ui.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import uk.co.dekoorb.android.cryptowatch.R

class CurrencyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)

        if (savedInstanceState == null) {
            val fragment = CurrencyListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .add(R.id.currency_fragment, fragment, CurrencyListFragment.TAG)
                    .commit()
        }
    }
}
