package com.dev.adi.collectapps.sorting

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.sorting.model.CommentModel
import kotlinx.android.synthetic.main.list_detail_item_product.view.*

class CommentAdapter(val list: MutableList<CommentModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_comment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update (index :Int, commentModel: CommentModel) {
        list.add(index, commentModel)
        notifyDataSetChanged()
    }

    fun update (commentModel: CommentModel) {
        list.add(commentModel)
        notifyDataSetChanged()
    }

    fun updateAll (index :Int, commentModel: MutableList<CommentModel>) {
        list.addAll(index, commentModel)
        notifyDataSetChanged()
    }

    fun updateAll (commentModel: MutableList<CommentModel>) {
        list.addAll(commentModel)
        notifyDataSetChanged()
    }

    fun clear () {
        list.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.txt0.text = list[position].body
        holder.itemView.txt1.text = list[position].name
        holder.itemView.txt2.text = list[position].email
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView)
}