package com.dev.adi.collectapps

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_histogram.*

class HistogramActivity : AppCompatActivity() {

    var bi: Bitmap? = null
    private val NUMBER_OF_COLOURS = 3
    private val SIZE = 256
    private val colourBins = arrayListOf<MutableList<Int>>()
    private val colourGray = arrayListOf<MutableList<Int>>()
    private val RED = 0
    private val GREEN = 1
    private val BLUE = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histogram)

        //importan
        val options = BitmapFactory.Options()
        options.inScaled = false
        bi = BitmapFactory.decodeResource(resources, R.drawable.test5, options)

        img.setImageBitmap(bi)

        colourBins?.clear()
        var size = 0
        while (size < NUMBER_OF_COLOURS) {
            colourBins?.add(arrayListOf())
            size++
        }

        MyTask().execute(bi)
//        button18.setOnClickListener {
//            img.setImageBitmap(intoGrayscale(bi))
//        }
    }

    private fun setBarChart() {
        val entries = ArrayList<Entry>()
        colourBins[RED].forEachIndexed { index, data -> entries.add(Entry(index.toFloat(), data.toFloat())) }
        val dataSet = LineDataSet(entries, "Red")
        dataSet.color = ResourcesCompat.getColor(resources, R.color.red, null)
        dataSet.valueTextColor = ResourcesCompat.getColor(resources, R.color.red, null)
        val lineData = LineData(dataSet)
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.setDrawFilled(true)
        dataSet.setDrawValues(false)
        dataSet.fillColor = ContextCompat.getColor(this,R.color.red)
        dataSet.color = ContextCompat.getColor(this,R.color.red)
        dataSet.setDrawCircles(false)
        bar_chart.data = lineData
        bar_chart.xAxis.setDrawGridLines(false)
        bar_chart.setTouchEnabled(false)
        bar_chart.invalidate()

    }

    private fun getImageColor (bi: Bitmap?) {
        if (bi != null) {
            for (y in 0 until bi.height) {
                for (x in 0 until bi.width) {
                    var pixel = bi.getPixel(x, y)
                    colourBins!![RED].add(Color.red(pixel))
                    colourBins!![GREEN].add(Color.green(pixel))
                    colourBins!![BLUE].add(Color.blue(pixel))
                }
            }
            e("red", colourBins[0].size.toString())
            e("green", colourBins[1].size.toString())
            e("blue", colourBins[2].size.toString())
        }
    }

    private fun intoGrayscale (bi: Bitmap?): Bitmap? {
        val bitmap= bi?.copy(bi.config, true)
        if (bi != null) {
            for (y in 0 until bi.height) {
                for (x in 0 until bi.width) {
                    var p = bi.getPixel(x, y)
                    val a = p shr 24 and 0xff
                    val r = p shr 16 and 0xff
                    val g = p shr 8 and 0xff
                    val b = p and 0xff

                    val avg = (r + g + b) / 3
                    p = a shl 24 or (avg shl 16) or (avg shl 8) or avg
                    bitmap?.setPixel(x, y, p)
                }
            }
        }
        return bitmap
    }

    inner class MyTask : AsyncTask<Bitmap, String, Boolean>() {
        private var pd = ProgressDialog(this@HistogramActivity)
        override fun doInBackground(vararg p0: Bitmap?): Boolean? {
            var isLoad = false
            p0[0].let {
//                intoGrayscale(it)
                getImageColor(it)
                isLoad = true
            }
            return isLoad
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            if (result!!) {
                setBarChart()
                //todo
            } else {
                //todo
            }
        }
    }
}
