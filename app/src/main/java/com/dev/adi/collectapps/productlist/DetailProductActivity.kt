package com.dev.adi.collectapps.productlist

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import com.dev.adi.collectapps.Helpler
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.model.DetailProductResponse
import com.dev.adi.collectapps.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_product.*

class DetailProductActivity : AppCompatActivity() {

    private val API_DETAIL_BL = "https://api.bukalapak.com/v2/products/"
    internal lateinit var productsDetail: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)
        val b = intent.extras
        val id = b!!.getString("id")
        Log.e("id", id)
        GetListProduct().execute(apiProductRequest(id))
    }

    @SuppressLint("StaticFieldLeak")
    inner class GetListProduct : AsyncTask<String, String, String>() {
        private var pd = ProgressDialog(this@DetailProductActivity)
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
                var respon = Gson().fromJson(result, DetailProductResponse::class.java)
                productsDetail = respon.product
                pd.dismiss()

                textView24.text = Html.fromHtml(productsDetail.name)
                textView28.text = Html.fromHtml(productsDetail.desc)

            }
        }
    }

    private fun apiProductRequest (id: String): String {
        val sb = StringBuilder(API_DETAIL_BL)
        sb.append("$id.json")
        return sb.toString()
    }
}
