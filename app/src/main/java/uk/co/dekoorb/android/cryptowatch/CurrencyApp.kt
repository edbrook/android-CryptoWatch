package uk.co.dekoorb.android.cryptowatch

import android.app.Application
import android.arch.persistence.room.Room
import android.os.AsyncTask
import uk.co.dekoorb.android.cryptowatch.db.CurrencyDatabase
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency

/**
 * Created by edbrook on 22/01/2018.
 */
class CurrencyApp: Application() {
    companion object {
        var DB_NAME = "currency_db"
        var db: CurrencyDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, CurrencyDatabase::class.java, DB_NAME).build()
        CreateFakeData().execute()
    }

    class CreateFakeData: AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            db?.currencyDao()?.addCurrency(createRandomCurrency())
            return null
        }
        private fun createRandomCurrency(): Currency {
            return Currency(id = "AAA", rank = 1, symbol = "BTC", priceGbp = Math.random() * 10000)
        }
    }

}