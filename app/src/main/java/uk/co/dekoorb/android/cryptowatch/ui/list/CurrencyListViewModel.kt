package uk.co.dekoorb.android.cryptowatch.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import uk.co.dekoorb.android.cryptowatch.CurrencyApp
import uk.co.dekoorb.android.cryptowatch.db.CurrencyDatabase
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency

/**
 * Created by edbrook on 22/01/2018.
 */
class CurrencyListViewModel: ViewModel() {

    private val mDb: CurrencyDatabase = CurrencyApp.db!!

    fun getCurrencyList(): LiveData<List<Currency>> {
        return mDb.currencyDao().getAllCurrencies()
    }

}