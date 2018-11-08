package com.dev.adi.collectapps.bukaMall

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.dev.adi.collectapps.Helpler
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.bukaMall.Adapter.MainSliderAdapter
import com.dev.adi.collectapps.bukaMall.Model.Products
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_product_act.*
import java.net.URL
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols



class DetailProductAct : AppCompatActivity() {

    private val API_DETAIL = "https://api.bukalapak.com/v2/products/"
    internal lateinit var productDetail : Products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product_act)
        val extra = intent.extras
        val id = extra.getString("id")
        GetListProduct().execute(apiProductRequest(id))

        img_down.setOnClickListener {
            img_down.visibility = View.GONE
            img_up.visibility = View.VISIBLE
            tv_desc.maxLines = Integer.MAX_VALUE
        }

        img_up.setOnClickListener {
            img_down.visibility = View.VISIBLE
            img_up.visibility = View.GONE
            tv_desc.maxLines = 5
        }
    }

    private fun apiProductRequest (id: String): String {
        val sb = StringBuilder(API_DETAIL)
        sb.append("$id.json")
        return sb.toString()
    }

    @SuppressLint("StaticFieldLeak")
    inner class GetListProduct : AsyncTask<String, String, String>() {
        private var pd = ProgressDialog(this@DetailProductAct)
        override fun doInBackground(vararg params: String?): String? {
            var stream: String?
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
                if (result.contains("Error")) {
                    pd.dismiss()
                    return
                }
                var respon = Gson().fromJson(result, com.dev.adi.collectapps.bukaMall.Model.DetailProductResponse::class.java)
                productDetail = respon.product
                pd.dismiss()
                title = "Detail Product"

                tv_name.text = Html.fromHtml(productDetail.name)
                tv_price.text = convertMoney(productDetail.price)
                tv_desc.text = Html.fromHtml(productDetail.desc)
                tv_seller_name.text = productDetail.seller_name
                tv_feedback.text = "${productDetail.seller_positive_feedback} feedback"
                tv_city.text = productDetail.city
                DownLoadImageTask(ic_seller).execute(productDetail.seller_avatar)
                tv_stock.text = productDetail.stock.toString()
                tv_sell.text = productDetail.sold_count.toString()
                tv_view.text = productDetail.view_count.toString()
                tv_peminat.text = productDetail.interest_count.toString()
                banner_slider1.setAdapter(MainSliderAdapter(productDetail.images))
            }
        }
    }

    private fun convertMoney (price : Int) : String {
        val kurs = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val format = DecimalFormatSymbols()

        format.currencySymbol = "Rp "
        format.monetaryDecimalSeparator = ','
        format.groupingSeparator = '.'

        kurs.decimalFormatSymbols = format
        return kurs.format(price)
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
