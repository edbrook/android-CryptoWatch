package uk.co.dekoorb.android.cryptowatch.db

import android.os.AsyncTask
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency

/**
 * Created by edbrook on 23/01/2018.
 */
class DbInsertTask(db: CurrencyDatabase): AsyncTask<List<Currency>, Unit, Unit>() {
    private val mDb = db

    override fun doInBackground(vararg currencies: List<Currency>?) {
        if (currencies.isNotEmpty()) {
            val theList = currencies[0]
            if (theList != null) {
                mDb.currencyDao().addCurrencies(theList)
            }
        }
    }
}