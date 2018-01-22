package uk.co.dekoorb.android.cryptowatch.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by edbrook on 22/01/2018.
 */

@Entity
data class Currency(
    @PrimaryKey
    var id: String = "",

    var name: String = "",
    var symbol: String = "",
    var rank: Int = -1,

    var priceUsd: Double = 0.0,
    var priceBtc: Double = 0.0,
    var priceGbp: Double = 0.0,

    var volumeUsd: Double = 0.0,
    var volumeGbp: Double = 0.0,

    var marketCapUsd: Double = 0.0,
    var marketCapGbp: Double = 0.0,

    var availableSupply: Double = 0.0,
    var totalSupply: Double = 0.0,
    var maxSupply: Double = 0.0,

    var percentChange1h: Float = 0.0f,
    var percentChange7d: Float = 0.0f,
    var percentChange24h: Float = 0.0f,

    var lastUpdated: Int = 0
)