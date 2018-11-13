package com.dev.adi.collectapps.singleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_numeric.*

class NumericActivity : AppCompatActivity() {

    var nomina = arrayOf("", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan", "sepuluh", "sebelas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numeric)

        button2.setOnClickListener {
            textView5.text = bilangx(editText2.text.toString().toDouble())
        }
    }

    private fun bilangx(angka: Double): String {
        if (angka < 12) {
            return nomina[angka.toInt()]
        }

        if (angka in 12.0..19.0) {
            return nomina[angka.toInt() % 10] + " belas "
        }

        if (angka in 20.0..99.0) {
            return nomina[angka.toInt() / 10] + " puluh " + nomina[angka.toInt() % 10]
        }

        if (angka in 100.0..199.0) {
            return "seratus " + bilangx(angka % 100)
        }

        if (angka in 200.0..999.0) {
            return nomina[angka.toInt() / 100] + " ratus " + bilangx(angka % 100)
        }

        if (angka in 1000.0..1999.0) {
            return "seribu " + bilangx(angka % 1000)
        }

        if (angka in 2000.0..999999.0) {
            return bilangx((angka.toInt() / 1000).toDouble()) + " ribu " + bilangx(angka % 1000)
        }

        return if (angka in 1000000.0..9.99999999E8) {
            bilangx((angka.toInt() / 1000000).toDouble()) + " juta " + bilangx(angka % 1000000)
        } else ""

    }
}
