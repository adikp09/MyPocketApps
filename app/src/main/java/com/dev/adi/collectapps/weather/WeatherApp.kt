package com.dev.adi.collectapps.weather

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dev.adi.collectapps.Helpler
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.helpermodul.Network
import com.dev.adi.collectapps.model.ForcastResponse
import com.dev.adi.collectapps.model.Lists
import com.dev.adi.collectapps.model.WeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_weather_app.*
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class WeatherApp : AppCompatActivity() {

    var icon : String = "03n"
    internal lateinit var weatherResponse : WeatherResponse
    internal lateinit var forcastResponse: ForcastResponse
    internal lateinit var adapter: ForcastAdapter
    internal lateinit var adapter2: ForcastAdapter2
    internal lateinit var adapter3: ForcastAdapter3
    var listAllWeather = arrayListOf<MutableList<Lists>>()
    var listWeather = arrayListOf<Lists>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_app)
        GetWeathers().execute(Network.apiRequest())
    }

    @SuppressLint("StaticFieldLeak")
    inner class GetWeathers : AsyncTask<String, String, String>() {
        private var pd = ProgressDialog(this@WeatherApp)
        override fun doInBackground(vararg params: String?): String? {
            var stream : String? = null
            var urlString = params[0]
            val http = Helpler()
            stream = http.getUrlData(urlString!!)
            return stream
        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd.setTitle("Please Wait..")
            pd.show()
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != null) {
                if (result.contains("Error not found city")) {
                    pd.dismiss()
                    return
                }
                val gson = Gson()
                val mType = object:TypeToken<WeatherResponse > () {}.type

                weatherResponse= gson.fromJson<WeatherResponse>(result, mType)
                pd.dismiss()

                textView36.text = "${getDateTime(weatherResponse.dt.toString())}"
                textView14.text = upperCaseFirst(weatherResponse.name + ", " + weatherResponse.sys.country)
                textView33.text = upperCaseFirst(weatherResponse.weather[0].description)
                textView17.text = "${Math.round(weatherResponse.main.temp)} \u2103"
                textView37.text = "Sunrise ${getTime(weatherResponse.sys.sunrise.toString())}"
                textView38.text = "Sunset ${getTime(weatherResponse.sys.sunset.toString())}"

                textView47.text = "${weatherResponse.wind.speed} m/s, ${weatherResponse.wind.deg}"
                textView46.text = "${weatherResponse.clouds.all} %"
                textView44.text = "${weatherResponse.main.pressure} hpa"
                textView42.text = "${weatherResponse.main.humidity} %"
                textView40.text = "${weatherResponse.rain.hour} mm"
                Glide.with(this@WeatherApp)
                        .load("http://api.openweathermap.org/img/w/${weatherResponse.weather[0].icon}.png")
                        .into(imageView2)

            }
        }
    }

    private fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("HH:mm MMM dd")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun getTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("HH:mm")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun upperCaseFirst(value : String): String {
        val array = value.toCharArray()
        array[0] = Character.toUpperCase(array[0])
        return String(array)
    }

    inner class DownLoadImageTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg urls: String): Bitmap? {
            val urlOfImage = urls[0]
            return try {
                val inputStream = URL(urlOfImage).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) { // Catch the download exception
                e.printStackTrace()
                null
            }
        }
        override fun onPostExecute(result: Bitmap?) {
            if(result!=null){
                // Display the downloaded image into image view
                Toast.makeText(imageView.context,"Fetching success",Toast.LENGTH_SHORT).show()
                imageView.setImageBitmap(result)
                imageView.layoutParams.height = resources.getDimension(R.dimen.icon).toInt()
                imageView.layoutParams.width = resources.getDimension(R.dimen.icon).toInt()
//                GetFotcast().execute(Network.apiForcastRequest())
            }else{
                Toast.makeText(imageView.context,"Error Fetching",Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class GetFotcast : AsyncTask<String, String, String>() {
        private var pd = ProgressDialog(this@WeatherApp)
        override fun doInBackground(vararg params: String?): String? {
            var stream : String? = null
            var urlString = params[0]
            val http = Helpler()
            stream = http.getUrlData(urlString!!)
            return stream
        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd.setTitle("Please Wait..")
            pd.show()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != null) {
                if (result.contains("city not found")) {
                    pd.dismiss()
                    return
                }
                val gson = Gson()
                val mType = object:TypeToken<ForcastResponse > () {}.type
                forcastResponse = gson.fromJson<ForcastResponse>(result, mType)
                pd.dismiss()

                var i = 0
                var tempDate = Date()
                var tempString = tempDate.toSimpleDateString()
                listAllWeather.add(arrayListOf())
                forcastResponse.list.forEachIndexed { index, weatherItem ->
                    val cal = Calendar.getInstance()
                    cal.time = Date(weatherItem.dt*1000)
                    tempDate = cal.time
                    if (tempString == Date(weatherItem.dt*1000).toSimpleDateString()) {
                        val tempTime = Date(weatherItem.dt*1000).toSimpleTimeWithoutSecond()
                        if (tempTime == "07:00" || tempTime == "16:00" || tempTime == "10:00" || tempTime == "22:00" || tempTime == "19:00") listAllWeather[i].add(weatherItem)
                    } else {
                        i++
                        listAllWeather.add(arrayListOf())
                        tempString = Date(weatherItem.dt*1000).toSimpleDateString()
                        val tempTime = Date(weatherItem.dt*1000).toSimpleTimeWithoutSecond()
                        if (tempTime == "07:00" || tempTime == "16:00" || tempTime == "10:00" || tempTime == "22:00" || tempTime == "19:00") listAllWeather[i].add(weatherItem)
                    }
                }

                adapter = ForcastAdapter(listAllWeather[0])
                list_forcast.layoutManager = GridLayoutManager(this@WeatherApp, 4)
                list_forcast.itemAnimator = DefaultItemAnimator()
                list_forcast.adapter = adapter

                adapter2 = ForcastAdapter2(listAllWeather[1])
                list_forcast2.layoutManager = GridLayoutManager(this@WeatherApp, 4)
                list_forcast2.itemAnimator = DefaultItemAnimator()
                list_forcast2.adapter = adapter

                adapter3 = ForcastAdapter3(listAllWeather[1])
                list_forcast3.layoutManager = GridLayoutManager(this@WeatherApp, 4)
                list_forcast3.itemAnimator = DefaultItemAnimator()
                list_forcast3.adapter = adapter
            }
        }
    }

    fun toJSON(collection: Collection<Int>): String {
        val sb = StringBuilder()
        sb.append("[")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            sb.append(element)
            if (iterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append("]")
        return sb.toString()
    }

    fun Date.toSimpleDateString() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this)

    fun Date.toSimpleTimeWithoutSecond() = SimpleDateFormat("HH:mm", Locale.getDefault()).format(this)
}