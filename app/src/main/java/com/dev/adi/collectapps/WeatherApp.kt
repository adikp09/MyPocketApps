package com.dev.adi.collectapps

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

                textView14.text = weatherResponse.name + ", " + weatherResponse.sys.country
                textView15.text = weatherResponse.weather[0].main + " (${weatherResponse.weather[0].description})"
                textView16.text = weatherResponse.main.humidity.toString() + "%"
                textView17.text = weatherResponse.main.temp.toString() + "C"

                icon = weatherResponse.weather[0].icon
                DownLoadImageTask(imageView2).execute("http://api.openweathermap.org/img/w/$icon.png")
            }
        }
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
                imageView.layoutParams.height = 100
                imageView.layoutParams.width = 100
                GetFotcast().execute(Network.apiForcastRequest())
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

    fun Date.toSimpleDateString() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this)

    fun Date.toSimpleTimeWithoutSecond() = SimpleDateFormat("HH:mm", Locale.getDefault()).format(this)
}