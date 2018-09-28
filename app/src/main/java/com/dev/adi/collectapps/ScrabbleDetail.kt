package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_scrabble_detail.*

class ScrabbleDetail : AppCompatActivity() {
    private var wordButton = arrayOfNulls<Button>(7)
    private var wordParams = arrayOfNulls<String>(7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrabble_detail)

        wordParams = intent.extras.getStringArray("playerLatter")

        wordButton = arrayOf(btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7)

        tv_player_turn.text = "Please pick your letter"

        wordParams.mapIndexed { index, letter ->
            if (letter != null) {
                wordButton[index]?.visibility = View.VISIBLE
                wordButton[index]?.setOnClickListener {
                    val mIntent = intent
                    mIntent.putExtra("wordPick", wordParams[index])
                    mIntent.putExtra("indexButton", intent.extras.getInt("indexButton"))
                    mIntent.putExtra("indexLine", intent.extras.getInt("indexLine"))
                    mIntent.putExtra("turn", intent.extras.getInt("turn"))
                    setResult(10004, mIntent)
                    finish()
                }
                wordButton[index]?.text = wordParams[index]
            }
        }
    }
}
