package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_antre.*

class AntreActivity : AppCompatActivity() {

    private var listAntre = arrayListOf<Int>()
    var indexAntre = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antre)

        button13.setOnClickListener {
            listAntre.add(indexAntre)
            indexAntre++
            lala()
        }

        button8.setOnClickListener {
            if (listAntre.size > 0) {
                setText(listAntre.first().toString(), textView19)
                listAntre.removeAt(0)
                lala()
            } else {
                setText("list Empty", textView19)
            }
        }
    }

    fun lala () {
        textView20.text = ""
        listAntre.forEach {
            textView20.append(it.toString())
        }
    }

    private fun setText (string : String, view : TextView) {
        view.text = string
    }
}
