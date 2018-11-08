package com.dev.adi.collectapps.bukaMall

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.bukaMall.Adapter.BrandsAdapter
import kotlinx.android.synthetic.main.activity_buka_mall.*

class BukaMallActivity : AppCompatActivity(), BrandsAdapter.onClickListener {

    private val brands: ArrayList<String> = ArrayList()
    internal lateinit var adapter: BrandsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buka_mall)

        addBrands()
        adapter = BrandsAdapter(brands, this, this@BukaMallActivity)
        rv_list_brand.layoutManager = LinearLayoutManager(this)
        rv_list_brand.layoutManager = GridLayoutManager(this, 2)
        rv_list_brand.adapter = adapter
    }

    override fun onClickItem(keyword: String) {
        val i = Intent(this, DetailListProductAct::class.java)
        i.putExtra("keyword", keyword)
        startActivity(i)
    }

    private fun addBrands () {
        brands.add("samsung")
        brands.add("xiaomi")
        brands.add("sony")
        brands.add("razer")
        brands.add("netgear")
        brands.add("sonos")
        brands.add("nokia")
        brands.add("sonos2")
        brands.add("sonos1")
        brands.add("sonos")
        brands.add("sonos")
        brands.add("nokia")
        brands.add("sonos2")
        brands.add("sonos1")
        brands.add("sonos")

    }

}
