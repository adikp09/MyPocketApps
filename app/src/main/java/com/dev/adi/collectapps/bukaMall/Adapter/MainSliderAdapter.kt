package com.dev.adi.collectapps.bukaMall.Adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import android.widget.Toast
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder
import java.net.URL


class MainSliderAdapter (val list: List<String>) : SliderAdapter() {

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        DownLoadImageTask(viewHolder.imageView).execute(list[position])
    }

    inner class DownLoadImageTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg urls: String): Bitmap? {
            val urlOfImage = urls[0]
            return try {
                val inputStream = URL(urlOfImage).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        override fun onPostExecute(result: Bitmap?) {
            if(result!=null){
                imageView.setImageBitmap(result)
            }else{
                Toast.makeText(imageView.context,"Error Fetching", Toast.LENGTH_SHORT).show()
            }
        }
    }
}