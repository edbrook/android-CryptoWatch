package uk.co.dekoorb.android.cryptowatch.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.dekoorb.android.cryptowatch.CurrencyApp
import uk.co.dekoorb.android.cryptowatch.db.CurrencyDatabase
import uk.co.dekoorb.android.cryptowatch.db.DbInsertTask
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency
import uk.co.dekoorb.android.cryptowatch.net.CoinMarketCapService
import uk.co.dekoorb.android.cryptowatch.net.CoinMarketCapServiceBuilder

/**
 * Created by edbrook on 22/01/2018.
 */
class CurrencyListViewModel: ViewModel() {
    companion object {
        const val TARGET_CURRENCY = "GBP"
    }

    private val mApi: CoinMarketCapService = CoinMarketCapServiceBuilder().getCoinMarketCapService()
    private val mDb: CurrencyDatabase = CurrencyApp.db!!
    private var mHaveUpdated: Boolean = false
    private val mUpdating: MutableLiveData<Boolean> = MutableLiveData()

    private val mNameFilter: MutableLiveData<String> = MutableLiveData()
    private val mCurrencyResult: LiveData<List<Currency>> = Transformations
            .switchMap(mNameFilter, { name -> loadCurrencyData("$name%") })

    init {
        mNameFilter.value = ""
    }

    private fun loadCurrencyData(name: String): LiveData<List<Currency>> {
        return if (name.isEmpty()) {
            mDb.currencyDao().getAllCurrencies()
        } else {
            mDb.currencyDao().filterCurrency(name)
        }
    }

    fun getCurrencyList(): LiveData<List<Currency>> {
        if (!mHaveUpdated) {
            updateCurrencies()
            mHaveUpdated = true
        }
        return mCurrencyResult
    }

    fun getFilter(): String? {
        return mNameFilter.value
    }

    fun setFilter(name: String) {
        mNameFilter.value = name
    }

    fun isUpdating(): LiveData<Boolean> {
        return mUpdating
    }

    fun updateCurrencies() {
        mUpdating.value = true
        val call = mApi.getConvertedCurrencies(TARGET_CURRENCY)
        call.enqueue(CurrencyListCallback())
    }

    private inner class CurrencyListCallback: Callback<List<Currency>> {
        override fun onFailure(call: Call<List<Currency>>?, t: Throwable?) {
            mUpdating.value = false
        }

        override fun onResponse(call: Call<List<Currency>>?, response: Response<List<Currency>>?) {
            val dbTask = DbInsertTask(mDb)
            dbTask.setCallback(object: DbInsertTask.Companion.DbInsertTaskComplete {
                override fun onDbInsertTaskComplete() {
                    mUpdating.value = false
                }
            })
            dbTask.execute(response?.body())
        }
    }
}