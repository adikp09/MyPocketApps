package com.dev.adi.collectapps.singleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_bmi.*



class BmiActivity : AppCompatActivity() {

    private var heights: EditText? = null
    private var weights: EditText? = null
    private var results: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)
        heights = height
        weights = weight
        results = result
    }

    fun calculateBMI(v: View) {
        val heightStr = height.text.toString()
        val weightStr = weight.text.toString()

        if (heightStr != null && "" != heightStr
                && weightStr != null && "" != weightStr) {
            val heightValue = java.lang.Float.parseFloat(heightStr) / 100
            val weightValue = java.lang.Float.parseFloat(weightStr)

            val bmi = weightValue / (heightValue * heightValue)

            results(bmi)
        }
    }

    private fun results(bmi: Float) {
        var bmiLabel = ""

        if (java.lang.Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.severe_thinness)
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.moderate_thinness)
        } else if (java.lang.Float.compare(bmi, 16f) > 0 && java.lang.Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.mild_thinness)
        } else if (java.lang.Float.compare(bmi, 18.5f) > 0 && java.lang.Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal)
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight)
        } else if (java.lang.Float.compare(bmi, 30f) > 0 && java.lang.Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i)
        } else if (java.lang.Float.compare(bmi, 35f) > 0 && java.lang.Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii)
        } else {
            bmiLabel = getString(R.string.obese_class_iii)
        }

        bmiLabel = bmi.toString() + "\n\n" + bmiLabel
        result.text = bmiLabel
    }
}
