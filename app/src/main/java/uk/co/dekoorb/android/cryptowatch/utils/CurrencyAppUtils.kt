package uk.co.dekoorb.android.cryptowatch.utils

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * Created by ed on 31/01/18.
 */

object CurrencyAppUtils {
    private const val KEY_BASE_URL = "base_url"
    private const val KEY_COINS = "coins"
    private const val KEY_ICON = "icon"

    fun loadIconUrlsFromFile(context: Context, file: String): HashMap<String, String> {
        val map = HashMap<String, String>()

        try {
            val reader = context.assets.open(file).bufferedReader()
            val json = JSONObject(reader.readText())
            val baseUrl = json[KEY_BASE_URL]
            val coins = json.getJSONObject(KEY_COINS)

            for (sym in coins.keys().asSequence().sorted()) {
                val coin = coins.getJSONObject(sym)
                map[sym] = "$baseUrl" + coin[KEY_ICON]
            }
        } catch (err: IOException) {
            //
        } catch (err: JSONException) {
            //
        }

        return map
    }
}
