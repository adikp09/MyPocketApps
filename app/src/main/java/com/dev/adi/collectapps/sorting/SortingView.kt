package com.dev.adi.collectapps.sorting

import com.dev.adi.collectapps.sorting.base.BaseView
import com.dev.adi.collectapps.sorting.model.CommentModel

interface MainView : BaseView{
    fun successGetData()
    fun updateData(commentModel: MutableList<CommentModel>)
    fun resetData (commentModel: MutableList<CommentModel>)
    fun dataNotFound ()
    fun error()
}