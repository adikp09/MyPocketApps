package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_age.*
import java.text.SimpleDateFormat
import java.util.*

class CalculateAge : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)
        button.setOnClickListener {
            textView2.text = calculateAge(editText.text.toString(), Date()).toString() + " years old" }
    }

    private fun calculateAge(birthDate: String, currentDate: Date): Int {
        val formatter = SimpleDateFormat("yyyyMMdd")
        val d1 = Integer.parseInt(birthDate)
        val d2 = Integer.parseInt(formatter.format(currentDate))
        return (d2 - d1) / 10000
    }
}
