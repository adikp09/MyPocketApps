package com.dev.adi.collectapps

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_snake.*
import java.util.*

class Snake : AppCompatActivity() {

    private var arrLineButton = arrayOfNulls<MutableList<LinearLayout>>(5)
    private var arrLineTextPlayer = arrayOfNulls<MutableList<TextView>>(5)
    private var arrLineTextNumber = arrayOfNulls<MutableList<TextView>>(5)
    private var arrLineTextState = arrayOfNulls<MutableList<TextView>>(5)
    private var arrKotak = arrayListOf<LinearLayout>()
    private var arrTextNumber = arrayListOf<TextView>()
    private var arrTextPlayer = arrayListOf<TextView>()
    private var arrTextState = arrayListOf<TextView>()
    private var arrLadder = arrayListOf<Ladder>()
    private var arrSnake = arrayListOf<Ladder>()
    private var random: Random = Random()
    private var isCreateBattleField = true
    private var turn = 1
    private var positionP1 = 0
    private var positionP2 = 0
    private var tap = 0

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake)

        tv_info.setOnClickListener {
            tap++
            if (tap == 5) {
                btnChit.visibility = View.VISIBLE
            }
        }

        if (tap == 0) {
            btnChit.visibility = View.GONE
        } else {
            btnChit.visibility = View.VISIBLE
        }

        var y = 0
        var x: Int
        while (y < 5) {
            val lineParent = LinearLayout(this)
            lineParent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lineParent.orientation = LinearLayout.HORIZONTAL

            val arrLineParent = arrayListOf<LinearLayout>()
            val arrTextPlayer = arrayListOf<TextView>()
            val arrTextNumber = arrayListOf<TextView>()
            val arrTextState = arrayListOf<TextView>()

            x = 0
            while (x < 5) {

                val lineChild = LinearLayout(this)
                val params = LinearLayout.LayoutParams(200, 200)
                params.setMargins(2,2,2,2)
                lineChild.layoutParams = params
                lineChild.orientation = LinearLayout.VERTICAL
                lineChild.gravity = Gravity.CENTER_VERTICAL
                val border = GradientDrawable()
                border.setColor(-0x1) //white background
                border.setStroke(1, -0x1000000) //black border with full opacity
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    lineChild.setBackgroundDrawable(border)
                } else {
                    lineChild.background = border
                }
                lineChild.setPadding(5,5,5,5)

                val text2 = TextView(this)
                text2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                text2.textSize = 12F
                lineChild.addView(text2)
                arrTextNumber.add(text2)

                val text1 = TextView(this)
                text1.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                text1.gravity = Gravity.CENTER
                text1.textSize = 20F
                lineChild.addView(text1)
                arrTextPlayer.add(text1)

                val text3 = TextView(this)
                text3.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                text3.gravity = Gravity.CENTER
                lineChild.addView(text3)
                arrTextState.add(text3)

                arrLineParent.add(lineChild)
                lineParent.addView(lineChild)

                x++
            }

            arrLineButton[y] = arrLineParent
            arrLineTextNumber[y] = arrTextNumber
            arrLineTextPlayer[y] = arrTextPlayer
            arrLineTextState[y] = arrTextState

            line_parent?.addView(lineParent)
            y++
        }

        y = 4
        while (y > -1) {
            if (y % 2 == 0) {
                x = 0
                while (x < 5) {
                    arrKotak.add(arrLineButton[y]!![x])
                    arrTextNumber.add(arrLineTextNumber[y]!![x])
                    arrTextPlayer.add(arrLineTextPlayer[y]!![x])
                    arrTextState.add(arrLineTextState[y]!![x])
                    x++
                }
            } else {
                x = 4
                while (x > -1) {
                    arrKotak.add(arrLineButton[y]!![x])
                    arrTextNumber.add(arrLineTextNumber[y]!![x])
                    arrTextPlayer.add(arrLineTextPlayer[y]!![x])
                    arrTextState.add(arrLineTextState[y]!![x])
                    x--
                }
            }
            y--
        }

        var number = 1
        arrTextNumber.map {
            it.text = number.toString()
            number++
        }

        arrTextState[0].text = "Start"
        arrTextPlayer[0].text = "P1-P2"
        arrTextState[arrTextState.size-1].text = "Finish"

        btn_add_snake.setOnClickListener {
            val mIntent = Intent(this, AddSnake::class.java)
            mIntent.putExtra("isAddSnake", true)
            startActivityForResult(mIntent, 1231)
        }
        btn_add_ladder.setOnClickListener {
            val mIntent = Intent(this, AddSnake::class.java)
            mIntent.putExtra("isAddLadder", true)
            startActivityForResult(mIntent, 1231)
        }
        btn_done.setOnClickListener {
            if (arrSnake.size > 0 && arrLadder.size > 0) {
                isCreateBattleField = false
                btn_add_snake.visibility = View.GONE
                btn_add_ladder.visibility = View.GONE
                btn_done.visibility = View.GONE
                floatingActionButton2.visibility = View.VISIBLE
                tv_info.text = "Player $turn turn"
            } else {
                Toast.makeText(this, "Obstacles can't empty", Toast.LENGTH_SHORT).show()
            }
        }

        btn1.setOnClickListener {
            playez(1)
        }

        btn2.setOnClickListener {
            playez(2)
        }

        btn3.setOnClickListener {
            playez(3)
        }

        btn4.setOnClickListener {
            playez(4)
        }

        btn5.setOnClickListener {
            playez(5)
        }

        btn6.setOnClickListener {
            playez(6)
        }

        floatingActionButton2.setOnClickListener {
            val roll = getRandom(6)
            val res1 = resources.getIdentifier("dice_$roll", "drawable", "com.dev.adi.collectapps")
            floatingActionButton2.setImageResource(res1)

            if (turn == 1) {
                if ((positionP1 + roll) == 24) {
                    arrTextPlayer[positionP2].text = ""
                    setGameOver()
                } else {
                    arrTextPlayer[positionP1].text = ""

                    if ((positionP1 + roll) < 24) positionP1 += roll else {
                        val tempP1 = positionP1 + roll
                        positionP1 = 24 - (tempP1-24)
                    }
                    positionP1 = getCheck(positionP1, arrLadder)
                    positionP1 = getCheck(positionP1, arrSnake)
                    Toast.makeText(this, "Player $turn got "+ roll +" and moves to "+ (positionP1+1), Toast.LENGTH_SHORT).show()
                    turn = 2
                    tv_info.text = "Player $turn turn"
                    setPosition()
                }
            } else {
                if ((positionP2 + roll) == 24) {
                    arrTextPlayer[positionP2].text = ""
                    setGameOver()
                } else {
                    arrTextPlayer[positionP2].text = ""
                    if ((positionP2 + roll) < 24) positionP2 += roll else {
                        val tempP2 = positionP2 + roll
                        positionP2 = 24 - (tempP2 - 24)
                    }
                    positionP2 = getCheck(positionP2, arrLadder)
                    positionP2 = getCheck(positionP2, arrSnake)
                    Toast.makeText(this, "Player $turn got "+ roll +" and moves to "+ (positionP2+1), Toast.LENGTH_SHORT).show()
                    turn = 1
                    tv_info.text = "Player $turn turn"
                    setPosition()
                }
            }
        }
        setPosition()
    }

    fun playez (rolls : Int) {
        val roll = rolls
        val res1 = resources.getIdentifier("dice_$roll", "drawable", "com.dev.adi.collectapps")
        floatingActionButton2.setImageResource(res1)

        if (turn == 1) {
            if ((positionP1 + roll) == 24) {
                arrTextPlayer[positionP2].text = ""
                setGameOver()
            } else {
                arrTextPlayer[positionP1].text = ""
                if ((positionP1 + roll) < 24) positionP1 += roll else {
                    val tempP1 = positionP1 + roll
                    positionP1 = 24 - (tempP1-24)
                }
                positionP1 = getCheck(positionP1, arrLadder)
                positionP1 = getCheck(positionP1, arrSnake)
                Toast.makeText(this, "Player $turn got "+ roll +" and moves to "+ (positionP1+1), Toast.LENGTH_SHORT).show()
                turn = 2
                tv_info.text = "Player $turn turn"
                setPosition()
            }
        } else {
            if ((positionP2 + roll) == 24) {
                arrTextPlayer[positionP2].text = ""
                setGameOver()
            } else {
                arrTextPlayer[positionP2].text = ""
                if ((positionP2 + roll) < 24) positionP2 += roll else {
                    val tempP2 = positionP2 + roll
                    positionP2 = 24 - (tempP2 - 24)
                }
                positionP2 = getCheck(positionP2, arrLadder)
                positionP2 = getCheck(positionP2, arrSnake)
                Toast.makeText(this, "Player $turn got "+ roll +" and moves to "+ (positionP2+1), Toast.LENGTH_SHORT).show()
                turn = 1
                tv_info.text = "Player $turn turn"
                setPosition()
            }
        }
    }

    private fun setPosition() {
        if (positionP1 == positionP2) {
            arrTextPlayer[positionP1].text = "P1-P2"
        } else {
            arrTextPlayer[positionP1].text = "P1"
            arrTextPlayer[positionP2].text = "P2"
        }
    }

    private fun getCheck(posisi: Int, list: MutableList<Ladder>): Int {
        var result = posisi
        list.mapIndexed { index, ladder ->
            if (posisi == (ladder.from-1)) {
                result = (ladder.to-1)
            }
        }
        return result
    }

    private fun setGameOver() {
        tv_info.text = if (turn == 1) "Player 1 Won" else "Player 2 Won"
        floatingActionButton2.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val arrRules = arrayListOf<IntArray>()
            arrRules.add(intArrayOf(1, 2, 3, 4, 5))
            arrRules.add(intArrayOf(10, 9, 8, 7, 6))
            arrRules.add(intArrayOf(11,12,13,14,15))
            arrRules.add(intArrayOf(20,19,18,17,16))
            arrRules.add(intArrayOf(21,22,23,24,25))

            val from = data.extras.getInt("from")
            val to = data.extras.getInt("to")
            if (data.extras.getBoolean("isAddLadder", false)) {
                if (from < to) {
                    arrRules.mapIndexed { index, ints ->
                        ints.map {
                            if (from == it) {
                                when {
                                    from > 24 || to > 24 || from == 1 || to == 1 -> Toast.makeText(this, "Starting point and end point are not allowed to be used", Toast.LENGTH_SHORT).show()
                                    ints.contains(to) -> Toast.makeText(this, "Failed, point cannot be in one line", Toast.LENGTH_SHORT).show()
                                    arrLadder.size > -1 -> {
                                        var isValid = true
                                        arrLadder.map {
                                            isValid = it.from != from && it.from != to && it.to != from && it.to != to
                                        }
                                        arrSnake.map {
                                            isValid = it.from != from && it.from != to && it.to != from && it.to != to
                                        }

                                        if (isValid) {
                                            arrLadder.add(Ladder(from, to))
                                        } else {
                                            Toast.makeText(this, "Already taken", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                    arrLadder.map {
                        arrTextState[it.from-1].text = "Landder to "+it.to
                    }
                } else {
                    Toast.makeText(this, "The starting point must be greater than the endpoint", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (from > to && !arrRules[0].contains(from)) {
                    arrRules.mapIndexed { index, ints ->
                        ints.map {
                            if (from == it) {
                                when {
                                    from > 24 || to > 24 || from == 1 || to == 1 -> Toast.makeText(this, "Starting point and end point are not allowed to be used", Toast.LENGTH_SHORT).show()
                                    ints.contains(to) -> Toast.makeText(this, "Failed, point cannot be in one line", Toast.LENGTH_SHORT).show()
                                    arrSnake.size > -1 -> {
                                        var isValid = true
                                        arrLadder.map {
                                            isValid = it.from != from && it.from != to && it.to != from && it.to != to
                                        }
                                        arrSnake.map {
                                            isValid = it.from != from && it.from != to && it.to != from && it.to != to
                                        }
                                        if (isValid) {
                                            arrSnake.add(Ladder(from, to))
                                        } else {
                                            Toast.makeText(this, "Already taken", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                    arrSnake.map {
                        arrTextState[it.from - 1].text = "Snake to " + it.to
                    }
                } else {
                    Toast.makeText(this, "The starting point can't ce less than the endpoint", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getRandom(length: Int) : Int {
        return random.nextInt(length)+1
    }

    class Ladder (
            val from: Int,
            val to: Int
    )
}
