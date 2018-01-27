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

    fun setCallback(listner: DbInsertTaskComplete) {
        mListener = listner
    }

    override fun doInBackground(vararg currencies: List<Currency>?) {
        if (currencies.isNotEmpty()) {
            val theList = currencies[0]
            if (theList != null) {
                mDb.currencyDao().addCurrencies(theList)
            }
        }
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        mListener?.onDbInsertTaskComplete()
    }
}