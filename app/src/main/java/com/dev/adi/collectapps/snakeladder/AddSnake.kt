package com.dev.adi.collectapps.snakeladder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_add_snake.*

class AddSnake : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_snake)
        button9.setOnClickListener {
            when {
                et_from.text.isEmpty() -> {
                    et_from.error = "Can't empty"
                }
                et_to.text.isEmpty() -> {
                    et_to.error = "Can't empty"
                }
                else -> {
                    val mIntent = intent
                    mIntent.putExtra("isAddLadder", intent.extras.getBoolean("isAddLadder", false))
                    mIntent.putExtra("isAddSnake", intent.extras.getBoolean("isAddSnake", false))
                    mIntent.putExtra("from", et_from.text.toString().toInt())
                    mIntent.putExtra("to", et_to.text.toString().toInt())
                    setResult(1211, mIntent)
                    finish()
                }
            }
        }
    }
}
