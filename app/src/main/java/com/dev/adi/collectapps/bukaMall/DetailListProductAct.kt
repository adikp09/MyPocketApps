package com.dev.adi.collectapps.bukaMall

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.e
import android.view.inputmethod.EditorInfo
import com.dev.adi.collectapps.bukaMall.Adapter.DetailListProductAdapter
import com.dev.adi.collectapps.bukaMall.Model.BukaMallProducts
import com.dev.adi.collectapps.bukaMall.Model.BukaMallResponse
import com.dev.adi.collectapps.EndlessOnScrollListener
import com.dev.adi.collectapps.Helpler
import com.dev.adi.collectapps.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_list_product.*

class DetailListProductAct : AppCompatActivity(), DetailListProductAdapter.onClickListener {

    val products: ArrayList<BukaMallProducts> = arrayListOf()
    internal lateinit var adapter: DetailListProductAdapter
    private var page = 1
    private val API_BL = "https://api.bukalapak.com/v2/products.json"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list_product)
        val extras = intent.extras
        val keyword = extras!!.getString("keyword")
        title = keyword.toString()
        GetDetailListProduct().execute(apiProductRequest(keyword.toString(), page))

        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                GetDetailListProduct().execute(apiProductRequest(keyword+" "+et_search.text, page))
                products.clear()
                adapter.notifyDataSetChanged()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        adapter = DetailListProductAdapter(products as MutableList<BukaMallProducts>, this@DetailListProductAct)
        list_products.layoutManager = LinearLayoutManager(this@DetailListProductAct)
        list_products.itemAnimator = DefaultItemAnimator()
        list_products.adapter = adapter
        list_products.addOnScrollListener(scrollData(page))
    }

    override fun onClickItem(id: String) {
        val i = Intent(this, DetailProductAct::class.java)
        i.putExtra("id", id)
        startActivity(i)
    }

    private fun scrollData(page: Int): EndlessOnScrollListener {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                val b = intent.extras
                val id = b!!.getString("keyword")
                GetDetailListProduct().execute(apiProductRequest(id, page))
            }
        }
    }

    fun apiProductRequest (q: String, page: Int): String {
        val sb = StringBuilder(API_BL)
        sb.append("?keywords=$q+tab&page=$page")
        return sb.toString()
    }

    @SuppressLint("StaticFieldLeak")
    inner class GetDetailListProduct : AsyncTask<String, String, String>() {
        private var pd = ProgressDialog(this@DetailListProductAct)
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

                var respon = Gson().fromJson(result, BukaMallResponse::class.java)
                e("meme", Gson().toJson(respon))
                products.addAll(respon.products)
                adapter.notifyDataSetChanged()
                pd.dismiss()
                page++
            }
        }
    }
}
