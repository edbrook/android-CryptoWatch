package uk.co.dekoorb.android.cryptowatch.ui

import uk.co.dekoorb.android.cryptowatch.db.entity.Currency

/**
 * Created by edbrook on 22/01/2018.
 */
interface ItemClickListener {
    fun onClick(currency: Currency)
    fun onLongClick(currency: Currency): Boolean
}
