package com.dev.adi.collectapps.tweat.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.categories.CategoriesParentAdapter
import com.dev.adi.collectapps.tweat.model.DataHome
import com.dev.adi.collectapps.tweat.model.DataSpecies
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter (val context : Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listData = arrayListOf<DataHome>()
    private var listSpecies = arrayListOf<DataSpecies>()

    fun addData(data: ArrayList<DataHome>) {
        listData.addAll(data)
        this.notifyDataSetChanged()
    }

    fun addSpecies(data: ArrayList<DataSpecies>) {
        listSpecies.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home, parent, false)
        return CategoriesParentAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.textView55.text = listData[position].species_id.toString()
        holder.itemView.textView56.text = listData[position].content

    }
}