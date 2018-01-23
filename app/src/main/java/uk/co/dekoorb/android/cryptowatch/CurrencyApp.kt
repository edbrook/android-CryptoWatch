package uk.co.dekoorb.android.cryptowatch

import android.app.Application
import android.arch.persistence.room.Room
import uk.co.dekoorb.android.cryptowatch.db.CurrencyDatabase

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
    }
}