package com.dev.adi.collectapps

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.dev.adi.collectapps.model.Products
import kotlinx.android.synthetic.main.list_item_product.view.*
import java.net.URL
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class ProductListAdapter(val list: MutableList<Products>, private val onClick: onClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        DownLoadImageTask(holder.itemView.ic_product).execute(list[position].images[0])
        holder.itemView.txt0.text = list[position].name
        holder.itemView.txt1.text = convertMoney(list[position].price)
        holder.itemView.txt3.text = list[position].seller_name
        holder.itemView.txt4.text = "${list[position].seller_positive_feedback} feedback"
        holder.itemView.setOnClickListener { onClick.onClickItem(list[position].id) }
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

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView)

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

    interface onClickListener {
        fun onClickItem(id: String)
    }
}