package com.dev.adi.collectapps.bukaMall.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.brands_item_list.view.*

class BrandsAdapter (val items : ArrayList<String>, val context: Context, private val onClick: onClickListener) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.brands_item_list, parent, false) as ViewGroup)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvAnimalType?.text =upperCaseFirst(items[position])
        holder?.itemView.setOnClickListener { onClick.onClickItem(items[position]) }

    }

    interface onClickListener {
        fun onClickItem(id: String)
    }

    private fun upperCaseFirst(value : String): String {
        val array = value.toCharArray()
        array[0] = Character.toUpperCase(array[0])
        return String(array)
    }
}

class ViewHolder (view: ViewGroup) : RecyclerView.ViewHolder(view) {
    val tvAnimalType = view.tv_brand_name!!
}
