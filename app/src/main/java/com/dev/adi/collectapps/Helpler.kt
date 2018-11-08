package com.dev.adi.collectapps

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class Helpler {

    fun getUrlData(urlString: String): String? {
        try {
            val urlConnection = URL(urlString).openConnection() as HttpURLConnection
            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val br = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val sb = StringBuilder()
                var line = br.readLine()
                sb.append(line)
                stream = sb.toString()
                Log.e("url", stream)
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stream
    }

    companion object {

        internal var stream: String? = null
    }
}
