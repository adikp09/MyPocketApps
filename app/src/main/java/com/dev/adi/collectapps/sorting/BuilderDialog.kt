package com.dev.adi.collectapps.sorting

import android.content.Context
import android.widget.Button
class BuilderDialog(val context : Context) {

    fun createDialog (button : Button, presenter: SortingPresenter) {
        button.setOnClickListener {
            val listItems = arrayOf("Name A-Z", "Name Z-A", "Email A-Z", "Email Z-A")
            val mBuilder = android.support.v7.app.AlertDialog.Builder(context)
            mBuilder.setTitle("Sort By")
            mBuilder.setItems(listItems) { _, which ->
                when (which) {
                    0 -> {
                        presenter.sortNameAZ()
                    }
                    1 -> {
                        presenter.sortNameZA()
                    }
                    2 -> {
                        presenter.sortEmailAZ()
                    }
                    3 -> {
                        presenter.sortEmailZA()
                    }
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }
    }
}