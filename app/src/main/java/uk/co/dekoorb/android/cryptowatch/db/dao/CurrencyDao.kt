package uk.co.dekoorb.android.cryptowatch.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency


/**
 * Created by edbrook on 22/01/2018.
 */

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency ORDER BY rank ASC")
    fun getAllCurrencies(): LiveData<List<Currency>>

    @Query("SELECT * FROM currency WHERE id = :id")
    fun getCurrency(id: String): LiveData<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCurrency(currency: Currency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCurrencies(currency: List<Currency>)

    @Update
    fun updateCurrency(currency: Currency)

    @Delete
    fun deleteCurrency(currency: Currency)
}