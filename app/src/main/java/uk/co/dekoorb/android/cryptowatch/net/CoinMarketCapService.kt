package uk.co.dekoorb.android.cryptowatch.net

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.dekoorb.android.cryptowatch.db.entity.Currency

/**
 * Created by edbrook on 23/01/2018.
 */

interface CoinMarketCapService {
    @GET("v1/ticker/")
    fun getConvertedCurrencies(@Query("convert") toCurrency: String): Call<List<Currency>>
}

class CoinMarketCapServiceBuilder {
    companion object {
        const val API_BASE_URL = "https://api.coinmarketcap.com/"
    }

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
                .registerTypeAdapter(Currency::class.java, CurrencyGsonDeserializer())
                .create()
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    fun getCoinMarketCapService(): CoinMarketCapService {
        val retrofit = getRetrofit()
        return retrofit.create(CoinMarketCapService::class.java)
    }
}