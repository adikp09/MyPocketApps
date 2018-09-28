package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_wtf.*
import java.util.*

class WtfActivity : AppCompatActivity() {
    private var position = arrayOfNulls<TextView>(3)
    private var y = 0
    private var x = 3
    private lateinit var random: Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wtf)
        position = arrayOf(button4, button5, button7)
//        x = random(y)
//        position.mapIndexed { index, buttons ->
//            if (index < y) {
//                buttons?.text = if (x == index) "2M" else ""
//                buttons?.setOnClickListener {
//                    var plus = index + 1
//                    AlertDialog.Builder(this)
//                        .setTitle("Anda yakin?")
//                        .setMessage("Kontestan memilih kotak nomor $plus")
//                        .setPositiveButton("Ya") { _, i ->
//                            AlertDialog.Builder(this)
//                                    .setTitle("Anda yakin?")
//                                    .setPositiveButton("Ya") { _, i ->
//                                        position.mapIndexed { indexx, buttonsx ->
//                                            if (index != indexx && indexx != x) {
//                                                buttonsx?.background = ColorDrawable(resources.getColor(android.R.color.holo_blue_dark))
//                                                if (buttons?.text == "2M") {
//                                                    itemChoose()
//                                                    Toast.makeText(this,"Kamu dapat 2M!", Toast.LENGTH_SHORT).show()
//                                                    buttons?.background = ColorDrawable(resources.getColor(android.R.color.holo_green_dark))
////                                                } else {
////                                                    Toast.makeText(this,"Zonk!", Toast.LENGTH_SHORT).show()
////                                                    buttons?.background = ColorDrawable(resources.getColor(android.R.color.holo_red_dark))
//                                                }
//                                            }
//                                        }
//                                    }
//                                    .setNegativeButton("Tidak") { _, i -> }.show()
//                        }
//                        .setNegativeButton("No") { _, i -> }.show()
//                }
//            }
//        }
    }

    private fun itemChoose () {
        position.map {
            it?.isEnabled = false
        }
    }

    private fun random(length: Int) : Int {
        random = Random()
        return random.nextInt(length)
    }
}
