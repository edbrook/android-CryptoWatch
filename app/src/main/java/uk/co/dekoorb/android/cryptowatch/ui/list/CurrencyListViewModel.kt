package uk.co.dekoorb.android.cryptowatch.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.dekoorb.android.cryptowatch.CurrencyApp
import uk.co.dekoorb.android.cryptowatch.db.CurrencyDatabase
import uk.co.dekoorb.android.cryptowatch.db.DbInsertTask
import uk.co.dekoorb.android.cryptowatch.db.dao.CurrencyDao
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

    fun getCurrencyList(): LiveData<List<Currency>> {
        updateCurrencies()
        return mDb.currencyDao().getAllCurrencies()
    }

    private fun updateCurrencies() {
        val call = mApi.getConvertedCurrencies(TARGET_CURRENCY)
        call.enqueue(CurrencyListCallback())
    }

    private inner class CurrencyListCallback: Callback<List<Currency>> {

        override fun onFailure(call: Call<List<Currency>>?, t: Throwable?) {
            System.out.println("ED-ERROR - get currencies failed!: " + t?.localizedMessage)
            if (t != null ) {
                throw t
            }
        }

        override fun onResponse(call: Call<List<Currency>>?, response: Response<List<Currency>>?) {
            DbInsertTask(mDb).execute(response?.body())
        }
    }
}