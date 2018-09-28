package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_temperature_conversion.*
import kotlin.math.roundToInt

class TemperatureConversion : AppCompatActivity() {

    var inputTemp = ""
    private var outputTemp = ""
    var temperatureTo = 0.0
    var temperatureFrom = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature_conversion)

        bt_input.setOnClickListener {
            val items = arrayOf<CharSequence>("celcius", "fahreneit")
            AlertDialog.Builder(this).setTitle("Select Temperature").setItems(items
            ) { dialog, which ->
                inputTemp = items[which].toString()
                bt_input.text = items[which]
            }.show()
        }

        bt_output.setOnClickListener {
            val items = arrayOf<CharSequence>("celcius", "fahreneit")
            AlertDialog.Builder(this).setTitle("Select Temperature").setItems(items
            ) { dialog, which ->
                outputTemp = items[which].toString()
                bt_output.text = items[which]
            }.show()
        }

        bt_calculate.setOnClickListener {
            if (inputTemp == "celcius") {
                when (outputTemp) {
                    "fahreneit" -> {
                        // C x 1,8 + 32
                        temperatureFrom = et_input.text.toString().toDouble().roundToInt().toDouble()
                        temperatureTo = temperatureFrom.times(1.8).plus(32)
                        et_output.setText(temperatureTo.toString())
                    }
                    else -> {
                        temperatureTo = temperatureFrom
                        et_output.setText(temperatureTo.toString())
                    }
                }
            } else if (inputTemp == "fahreneit") {
                when (outputTemp) {
                    "celcius" -> {
                        // (F - 32) / 1,8
                        temperatureFrom = et_input.text.toString().toDouble().roundToInt().toDouble()
                        temperatureTo = (temperatureFrom.minus(32)).div(1.8)
                        et_output.setText(temperatureTo.toString())
                    }
                    else -> {
                        temperatureTo = temperatureFrom
                        et_output.setText(temperatureTo.toString())
                    }
                }
            }
        }
    }
}
