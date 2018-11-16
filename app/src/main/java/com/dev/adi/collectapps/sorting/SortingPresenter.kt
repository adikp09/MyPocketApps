package com.dev.adi.collectapps.sorting

import android.os.AsyncTask
import com.dev.adi.collectapps.sorting.base.BasePresenter
import com.dev.adi.collectapps.sorting.model.CommentModel
import com.google.gson.Gson
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class SortingPresenter : BasePresenter<MainView> {

    private var modelView : MainView? = null
    private val dataComment: ArrayList<CommentModel> = arrayListOf()
    private val tempDataComment: ArrayList<CommentModel> = arrayListOf()
    private val getComment = GetComment()

    fun setTempDataComment (data : ArrayList<CommentModel>) {
        tempDataComment.addAll(data)
    }

    fun requestData () {
        getComment.execute("https://jsonplaceholder.typicode.com/comments")
    }

    fun sortNameAZ () {
        tempDataComment.sortWith(Comparator { t1, t2 ->
            t1.name.compareTo(t2.name)
        })
        resetData()
    }

    fun sortNameZA () {
        tempDataComment.sortWith(Comparator { t1, t2 ->
            t2.name.compareTo(t1.name)
        })
        resetData()
    }

    fun sortEmailAZ () {
        tempDataComment.sortWith(Comparator { t1, t2 ->
            t1.email.compareTo(t2.email)
        })
        resetData()
    }

    fun sortEmailZA () {
        tempDataComment.sortWith(Comparator { t1, t2 ->
            t2.email.compareTo(t1.email)
        })
        resetData()
    }

    private fun resetData () {
        modelView?.resetData(tempDataComment)
    }

    fun loadMore (page : Int) {
        if ((page * 10) + 10 <= tempDataComment.size) {
            modelView?.updateData(tempDataComment.subList(page * 10, (page * 10) + 10))
        } else if (tempDataComment.size >= (page * 10)) {
            modelView?.updateData(tempDataComment.subList(page * 10, tempDataComment.size))
        }
    }

    fun searchBody (q : String) {
        val result = dataComment.filter { it.body.contains(q.toLowerCase())}
        if (result.isEmpty()) {
            modelView?.dataNotFound()
        } else {
            tempDataComment.clear()
            tempDataComment.addAll(result)
            resetData()
        }
    }

    inner class GetComment : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            return try {
                URL(url[0]).readText()
            } catch (e : IOException) {
                "error"
            } catch (e : MalformedURLException) {
                "error"
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != "error") {
                var respon = Gson().fromJson(result, Array<CommentModel>::class.java)
                dataComment.addAll(respon)
                tempDataComment.addAll(dataComment)
                modelView?.successGetData()
                modelView?.updateData(tempDataComment)
            } else {
                modelView?.error()
            }
        }
    }

    fun stopTask () {
        getComment.isCancelled
    }

    override fun onDettach() {
        modelView = null
        getComment.isCancelled
    }

    override fun onAttach(view: MainView) {
        modelView = view
    }

}