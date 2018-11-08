package com.dev.adi.collectapps.categories

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.list_item_categories.view.*

class CategoriesParentAdapter (val list: MutableList<CategoriesModel>, val context : Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_categories, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        list[position].let {
            holder.itemView.txt0.text = it.name

            it.children.let { item ->
                if (item.isNotEmpty()) {
                    item.mapIndexed { index, categoriesModel ->
                        e("meme", categoriesModel.name)
                    }
                }
            }
        }
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView)
}