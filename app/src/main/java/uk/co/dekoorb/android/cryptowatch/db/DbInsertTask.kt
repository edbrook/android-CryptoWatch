package uk.co.dekoorb.android.cryptowatch.db

import android.os.AsyncTask
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency

/**
 * Created by edbrook on 23/01/2018.
 */
class DbInsertTask(private val mDb: CurrencyDatabase):
        AsyncTask<List<Currency>, Unit, Unit>() {

    companion object {
        interface DbInsertTaskComplete {
            fun onDbInsertTaskComplete()
        }
    }

    private var mListener: DbInsertTaskComplete? = null

    fun setCallback(listener: DbInsertTaskComplete) {
        mListener = listener
    }

    override fun doInBackground(vararg currencies: List<Currency>?) {
        if (currencies.isNotEmpty()) {
            val currency = currencies[0]
            if (currency != null) {
                mDb.currencyDao().addCurrencies(currency)
            }
        }
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        mListener?.onDbInsertTaskComplete()
    }
}