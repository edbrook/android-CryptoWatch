package uk.co.dekoorb.android.cryptowatch.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import uk.co.dekoorb.android.cryptowatch.CurrencyApp
import uk.co.dekoorb.android.cryptowatch.db.CurrencyDatabase
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency

/**
 * Created by edbrook on 23/01/2018.
 */
class CurrencyDetailViewModel: ViewModel() {
    private val mDb: CurrencyDatabase = CurrencyApp.db!!
    private val mCurrencyId: MutableLiveData<String> = MutableLiveData()
    private val mCurrency: LiveData<Currency> =
            Transformations.switchMap(mCurrencyId,
                    { id -> mDb.currencyDao().getCurrency(id) })

    fun setCurrency(id: String) {
        mCurrencyId.value = id
    }

    fun getCurrency(): LiveData<Currency> {
        return mCurrency
    }
}