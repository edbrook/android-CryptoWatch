package uk.co.dekoorb.android.cryptowatch.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import uk.co.dekoorb.android.cryptowatch.R

/**
 * Created by edbrook on 23/01/2018.
 */
class  CurrencyDetailActivity: AppCompatActivity() {

    companion object {
        const val KEY_CURRENCY_ID = "key::currency_id"

        fun getIntent(context: Context, currencyId: String): Intent {
            val intent = Intent(context, CurrencyDetailActivity::class.java)
            intent.putExtra(KEY_CURRENCY_ID, currencyId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_detail)

        if (savedInstanceState == null) {
            val currencyId = intent.getStringExtra(KEY_CURRENCY_ID)
            val fragment = CurrencyDetailFragment.newInstance(currencyId)
            supportFragmentManager.beginTransaction()
                    .add(R.id.currency_detail_frame, fragment, CurrencyDetailFragment.TAG)
                    .commit()
        }
    }
}