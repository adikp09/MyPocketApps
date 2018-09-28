package com.dev.adi.collectapps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.dev.adi.collectapps.model.Lists
import kotlinx.android.synthetic.main.list_item_forcast.view.*
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class ForcastAdapter(val list: MutableList<Lists>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForcastAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_forcast, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        DownLoadImageTask(holder.itemView.imgWeather).execute("http://api.openweathermap.org/img/w/${list[position].weather[0].icon}.png")
        holder.itemView.txt0.text = Date(list[position].dt).simpleTimeToDate()
        holder.itemView.txt1.text = list[position].weather[0].main
        holder.itemView.txt2.text = list[position].main.temp_min.toString() + " - " + list[position].main.temp_max.toString() + "C"
        holder.itemView.txt3.text = list[position].wind.speed.toString() + "km/h"
        holder.itemView.txt4.text = "10 km"
        holder.itemView.txt5.text = "0 mm | " + list[position].main.humidity.toString() + "%"
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView)

    private fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun String.fullDateTimeToDateWithoutSecond(context: Context): Date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", context.resources.configuration.locale).parse(this)

    fun Date.simpleTimeToDate() = SimpleDateFormat("HH:mm", Locale.getDefault()).format(this)

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
                imageView.setImageBitmap(result)
                imageView.layoutParams.height = 50
                imageView.layoutParams.width = 50
            }else{
                Toast.makeText(imageView.context,"Error Fetching", Toast.LENGTH_SHORT).show()
            }
        }
    }
}