package uk.co.dekoorb.android.cryptowatch.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency
import uk.co.dekoorb.android.cryptowatch.db.dao.CurrencyDao


/**
 * Created by edbrook on 22/01/2018.
 */
@Database(entities = arrayOf(Currency::class), version = 1, exportSchema = false)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}