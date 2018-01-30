package uk.co.dekoorb.android.cryptowatch.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import uk.co.dekoorb.android.cryptowatch.db.dao.CurrencyDao
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency


/**
 * Created by edbrook on 22/01/2018.
 */
@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}