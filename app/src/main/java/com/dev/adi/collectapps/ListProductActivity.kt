package com.dev.adi.collectapps

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import com.dev.adi.collectapps.model.ProductResponse
import com.dev.adi.collectapps.model.Products
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list_product.*

class ListProductActivity : AppCompatActivity(), ProductListAdapter.onClickListener {

    val products: ArrayList<Products> = arrayListOf()
    internal lateinit var adapter: ProductListAdapter
    private var page = 1
    private val API_BL = "https://api.bukalapak.com/v2/products.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)
        GetListProduct().execute(apiProductRequest("galaxy", page))

        et_search.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                GetListProduct().execute(apiProductRequest(et_search.text.toString(), 1))
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        adapter = ProductListAdapter( products as MutableList<Products>, this@ListProductActivity)
        list_products.layoutManager = LinearLayoutManager(this@ListProductActivity)
        list_products.itemAnimator = DefaultItemAnimator()
        list_products.adapter = adapter
        list_products.addOnScrollListener(scrollData(page))

    }

    @SuppressLint("StaticFieldLeak")
    inner class GetListProduct : AsyncTask<String, String, String>() {
        private var pd = ProgressDialog(this@ListProductActivity)
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
                if (result.contains("Error not found")) {
                    pd.dismiss()
                    return
                }

                var respon = Gson().fromJson(result, ProductResponse::class.java)

                products.addAll(respon.products)
                adapter.notifyDataSetChanged()
                pd.dismiss()
                page++
            }
        }
    }

    override fun onClickItem(position: String) {
        val i = Intent(this, DetailProductActivity::class.java)
        i.putExtra("id", position)
        startActivity(i)
    }

    fun apiProductRequest (q: String, page: Int): String {
        val sb = StringBuilder(API_BL)
        sb.append("?keywords=$q+tab&page=$page")
        return sb.toString()
    }

    private fun scrollData(page: Int): EndlessOnScrollListener {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                GetListProduct().execute(apiProductRequest("galaxy", page))
            }
        }
    }
}
