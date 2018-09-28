package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_ship.*

class AddShip : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ship)

        textView14.setOnClickListener {
            val listItems = arrayOf(
                    getString(R.string.horizontal),
                    getString(R.string.vertival)
            )
            val mBuilder = AlertDialog.Builder(this@AddShip)
            mBuilder.setTitle("Choose an item")
            mBuilder.setItems(listItems
            ) { _, which ->
                when {
                    listItems[which] == getString(R.string.horizontal) ->
                        textView14.text = getString(R.string.horizontal)
                    listItems[which] == getString(R.string.vertival) ->
                        textView14.text = getString(R.string.vertival)
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        textView15.setOnClickListener {
            val listItems = arrayOf(
                    getString(R.string.horizontal),
                    getString(R.string.vertival)
            )
            val mBuilder = AlertDialog.Builder(this@AddShip)
            mBuilder.setTitle("Choose an item")
            mBuilder.setItems(listItems
            ) { _, which ->
                when {
                    listItems[which] == getString(R.string.horizontal) ->
                        textView15.text = getString(R.string.horizontal)
                    listItems[which] == getString(R.string.vertival) ->
                        textView15.text = getString(R.string.vertival)
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        textView16.setOnClickListener {
            val listItems = arrayOf(
                    getString(R.string.horizontal),
                    getString(R.string.vertival)
            )
            val mBuilder = AlertDialog.Builder(this@AddShip)
            mBuilder.setTitle("Choose an item")
            mBuilder.setItems(listItems
            ) { _, which ->
                when {
                    listItems[which] == getString(R.string.horizontal) ->
                        textView16.text = getString(R.string.horizontal)
                    listItems[which] == getString(R.string.vertival) ->
                        textView16.text = getString(R.string.vertival)
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        textView17.setOnClickListener {
            val listItems = arrayOf(
                    getString(R.string.horizontal),
                    getString(R.string.vertival)
            )
            val mBuilder = AlertDialog.Builder(this@AddShip)
            mBuilder.setTitle("Choose an item")
            mBuilder.setItems(listItems
            ) { _, which ->
                when {
                    listItems[which] == getString(R.string.horizontal) ->
                        textView17.text = getString(R.string.horizontal)
                    listItems[which] == getString(R.string.vertival) ->
                        textView17.text = getString(R.string.vertival)
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        textView18.setOnClickListener {
            val listItems = arrayOf(
                    getString(R.string.horizontal),
                    getString(R.string.vertival)
            )
            val mBuilder = AlertDialog.Builder(this@AddShip)
            mBuilder.setTitle("Choose an item")
            mBuilder.setItems(listItems
            ) { _, which ->
                when {
                    listItems[which] == getString(R.string.horizontal) ->
                        textView18.text = getString(R.string.horizontal)
                    listItems[which] == getString(R.string.vertival) ->
                        textView18.text = getString(R.string.vertival)
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        button11.setOnClickListener {
            val mIntent = intent
            setResult(1211, mIntent)
            finish()
        }
    }
}
