package com.dev.adi.collectapps.sorting

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import com.dev.adi.collectapps.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sorting.*
import java.net.HttpURLConnection
import java.net.URL

class SortingActivity : AppCompatActivity() {
    private val dataComment: ArrayList<CommentModel> = arrayListOf()
    internal lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorting)

        GetComment().execute("https://jsonplaceholder.typicode.com/comments")

        adapter = CommentAdapter(dataComment as MutableList<CommentModel>)
        rv_coment.layoutManager = LinearLayoutManager(this@SortingActivity)
        rv_coment.itemAnimator = DefaultItemAnimator()
        rv_coment.adapter = adapter

        button18.setOnClickListener {
            val listItems = arrayOf("Name A-Z", "Name Z-A", "Email A-Z", "Email Z-A")
            val mBuilder = android.support.v7.app.AlertDialog.Builder(this)
            mBuilder.setTitle("Sort By")
            mBuilder.setItems(listItems) { _, which ->
                when (which) {
                    0 -> {
                        dataComment.sortWith(Comparator { t1, t2 ->
                            t1.name.compareTo(t2.name)
                        })
                    }
                    1 -> {
                        dataComment.sortWith(Comparator { t1, t2 ->
                            t2.name.compareTo(t1.name)
                        })
                    }
                    2 -> {
                        dataComment.sortWith(Comparator { t1, t2 ->
                            t1.email.compareTo(t2.email)
                        })
                    }
                    3 -> {
                        dataComment.sortWith(Comparator { t1, t2 ->
                            t2.email.compareTo(t1.email)
                        })
                    }
                }
                adapter.notifyDataSetChanged()
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val equal = dataComment.filter { it.body.contains(et_search.text.toString().toLowerCase())}
                dataComment.clear()
                dataComment.addAll(equal)
                adapter.notifyDataSetChanged()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class GetComment : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {

            var text: String
            var connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally { connection.disconnect() }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var respon = Gson().fromJson(result, Array<CommentModel>::class.java)
            dataComment.addAll(respon)
            adapter.notifyDataSetChanged()
        }
    }
}
