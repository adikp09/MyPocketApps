package com.dev.adi.collectapps.snakeladder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dev.adi.collectapps.R
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_snake_ledder.*
import java.util.*



class SnakeLedder : AppCompatActivity() {

    private val rand = Random()

    private val board = mapOf(
            3 to 8, 4 to 6, 9 to 2, 10 to 13, 12 to 18, 15 to 7, 18 to 23, 24 to 3
    )

    private val sixVal = true

    var turn = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake_ledder)
        val flexboxLayout = findViewById<FlexboxLayout>(R.id.flexbox_layout)
        flexboxLayout.flexDirection = FlexDirection.ROW

        val view = flexboxLayout.getChildAt(0)
        val lp = view.layoutParams as FlexboxLayout.LayoutParams
        lp.order = -1
        lp.flexGrow = 2F
        view.layoutParams = lp

        val players = intArrayOf(1)
        btn_roll.setOnClickListener {
            if (turn == 1) {
                for ((i, s) in players.withIndex()) {
                    val ns = turn(i + 1, s)
                    if (ns == 25) {
                        println("Player ${i+1} wins!")
                        return@setOnClickListener
                    }
                    players[i] = ns

                    println()
                }
            }
        }
    }

    private fun turn(player: Int, square: Int): Int {
        var square2 = square
        val roll = 1 + rand.nextInt(6)
        print("Player $player, on square $square2, rolls a $roll")
        textView12.text = "You got " + (roll).toString()
        if (square2 + roll > 25) {
            println(" stop")
        } else {
            square2 += roll
            println(" and moves to $square2.")
            if (square2 == 25) return 25
            val next = board.getOrDefault(square2, square2)
            Log.e("board", next.toString())
            if (square2 < next) {
                println("Landed on a ladder. up to $next.")
                if (next == 25) return 25
                square2 = next
                return square2
            } else if (square2 > next) {
                println("Landed on a snake. down to $next.")
                square2 = next
                return square2
            }
        }
        if (roll < 6 || !sixVal) return square2
        println("roll again.")
        return square2
    }
}
