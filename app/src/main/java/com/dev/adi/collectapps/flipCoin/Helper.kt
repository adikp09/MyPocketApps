package com.dev.adi.collectapps.flipCoin

import android.content.Context
import android.util.Log.e
import java.io.IOException

class Helper {
    companion object {
        fun loadJSONFromAsset(context: Context): String? {
            var json: String?
            try {
                val inputStream = context.assets.open("Coin.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            e("json", json)
            return json
        }
    }
}