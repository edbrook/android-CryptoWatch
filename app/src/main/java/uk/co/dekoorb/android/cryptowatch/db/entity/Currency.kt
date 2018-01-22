package uk.co.dekoorb.android.cryptowatch.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by edbrook on 22/01/2018.
 */

@Entity
data class Currency(
        @PrimaryKey
        val id: String,

        val name: String,
        val symbol: String,
        val rank: Int,

        val priceUsd: Double,
        val priceBtc: Double,
        val priceGbp: Double,

        val volumeUsd: Double,
        val volumeGbp: Double,

        val marketCapUsd: Double,
        val marketCapGbp: Double,

        val availableSupply: Double,
        val totalSupply: Double,
        val maxSupply: Double,

        val percentChange1h: Float,
        val percentChange7d: Float,
        val percentChange24h: Float,

        val lastUpdated: Int
)