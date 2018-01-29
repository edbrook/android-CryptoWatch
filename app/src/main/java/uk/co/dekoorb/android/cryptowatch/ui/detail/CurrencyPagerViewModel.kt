package uk.co.dekoorb.android.cryptowatch.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import uk.co.dekoorb.android.cryptowatch.CurrencyApp
import uk.co.dekoorb.android.cryptowatch.db.CurrencyDatabase

/**
 * Created by ed on 29/01/18.
 */
class CurrencyPagerViewModel: ViewModel() {

    private val mDb: CurrencyDatabase = CurrencyApp.db!!

    private var mCurrentPosition: Int = -1
    private val mFilter: MutableLiveData<String> = MutableLiveData()
    private val mCurrencyIdsList: LiveData<List<String>> = Transformations
            .switchMap(mFilter, { name ->
                if (name.isNullOrEmpty()) {
                    mDb.currencyDao().getAllCurrencyIds()
                } else {
                    mDb.currencyDao().filterCurrencyIds("$name%")
                }
            })

    fun getCurrencyIdList(): LiveData<List<String>> {
        return mCurrencyIdsList
    }

    fun setFilter(name: String?) {
        mFilter.value = name
    }

    fun getPosition(): Int {
        return mCurrentPosition
    }

    fun setPosition(position: Int) {
        mCurrentPosition = position
    }
}