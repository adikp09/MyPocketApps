package com.dev.adi.collectapps.singleapp

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_cashier.*

class CashierActivity : AppCompatActivity() {

    var value = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cashier)

        button3.setOnClickListener {
            value += (textView7.text.toString().toInt() * textView8.text.toString().toInt())
            textView9.text = value.toString()
        }

        textView6.setOnClickListener {
            val listItems = arrayOf(
                    getString(R.string.rokok),
                    getString(R.string.roti),
                    getString(R.string.nasi),
                    getString(R.string.aqua),
                    getString(R.string.teh_botol)

            )
            val mBuilder = AlertDialog.Builder(this@CashierActivity)
            mBuilder.setTitle("Choose an item")
            mBuilder.setItems(listItems
            ) { _, which ->
                when {
                    listItems[which] == getString(R.string.rokok) ->
                    {
                        textView6.text = getString(R.string.rokok).toString()
                        textView8.text = "20000"
                    }
                    listItems[which] == getString(R.string.roti) ->
                    {
                        textView6.text = getString(R.string.roti).toString()
                        textView8.text = "4000"
                    }
                    listItems[which] == getString(R.string.nasi) ->
                        {
                            textView6.text = getString(R.string.nasi).toString()
                            textView8.text = "6000"
                        }
                    listItems[which] == getString(R.string.aqua) ->
                        {
                            textView6.text = getString(R.string.aqua).toString()
                            textView8.text = "15000"
                        }
                    listItems[which] == getString(R.string.teh_botol) ->
                        {
                            textView6.text = getString(R.string.teh_botol).toString()
                            textView8.text = "5000"
                        }
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        textView7.setOnClickListener {
            val listItems = arrayOf(
                    getString(R.string.one),
                    getString(R.string.two),
                    getString(R.string.three),
                    getString(R.string.four),
                    getString(R.string.five),
                    getString(R.string.six),
                    getString(R.string.seven),
                    getString(R.string.eight),
                    getString(R.string.nine)
            )
            val mBuilder = AlertDialog.Builder(this@CashierActivity)
            mBuilder.setTitle("Choose an item")
            mBuilder.setItems(listItems
            ) { _, which ->
                when {
                    listItems[which] == getString(R.string.one) ->
                        textView7.text = "1"
                    listItems[which] == getString(R.string.two) ->
                        textView7.text = "2"
                    listItems[which] == getString(R.string.three) ->
                        textView7.text = "3"
                    listItems[which] == getString(R.string.four) ->
                        textView7.text = "4"
                    listItems[which] == getString(R.string.five) ->
                        textView7.text = "5"
                    listItems[which] == getString(R.string.six) ->
                        textView7.text = "6"
                    listItems[which] == getString(R.string.seven) ->
                        textView7.text = "7"
                    listItems[which] == getString(R.string.eight) ->
                        textView7.text = "8"
                    listItems[which] == getString(R.string.nine) ->
                        textView7.text = "9"

                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }
    }
}
