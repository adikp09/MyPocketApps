package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_minesweeper.*
import java.util.*

class MinesweeperActivity : AppCompatActivity() {

    private var arrayBoard = arrayListOf<LinearLayout>()
    private var arrayText = arrayListOf<TextView>()
    private var arrayBom = arrayListOf<Int>()
    private lateinit var random: Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minesweeper)

        button14.setOnClickListener {
            when {
                editText9.text.isEmpty() -> editText9.error = "Can not blank la"
                editText10.text.isEmpty() -> editText9.error = "Can not blank"
                editText9.text.toString().toInt() < 1 -> editText9.error = "Minimum value is 1 la"
                editText10.text.toString().toInt() < 0 -> editText10.error = "Minimum value is 1 la"
                else -> {
                    var index = 0
                    var bomCount = editText10.text.toString().toInt()
                    var boardLength = editText9.text.toString().toInt()
                    if (bomCount > boardLength) {
                        editText10.error = "Too much Bom la"
                    } else {
                        initialGame()
                        arrayBom.addAll(createRandomBom(bomCount, boardLength))
                        Log.e("Bom Position la", Gson().toJson(arrayBom))

                        while (index < boardLength) {
                            val lineChild = BoardMinesweeper(this).createBoard()
                            val textView = BoardMinesweeper(this).createTextView()
                            lineChild.addView(textView)

                            LinearLayoutx.addView(lineChild)
                            arrayBoard.add(lineChild)
                            arrayText.add(textView)
                            index++
                        }
                        initialBoard(arrayBoard.size)
                        onTilesClick()
                    }
                }
            }
        }
        button16.setOnClickListener {
            initialGame()
            linearLayout8.visibility = View.VISIBLE
            button16.visibility = View.GONE
        }
    }

    private fun initialGame() {
        arrayBom.clear()
        arrayBoard.clear()
        arrayText.clear()
        LinearLayoutx.removeAllViews()
        textView21.text = "Minesweeper 1D"
        button16.text = ":)"
    }

    private fun initialBoard (boardSize: Int) {
        editText9.setText("")
        editText10.setText("")
        linearLayout8.visibility = View.GONE
        button16.visibility = View.VISIBLE
        arrayBom.forEach {
            arrayText[it].tag = "*"
            if (it-1 > -1 && arrayText[it-1].tag.toString() != "*") arrayText[it-1].tag = arrayText[it-1].tag.toString().toInt() + 1
            if (it+1 < boardSize && arrayText[it+1].tag.toString() != "*") arrayText[it+1].tag = arrayText[it+1].tag.toString().toInt() + 1
        }
    }

    private fun onTilesClick () {
        arrayBoard.forEachIndexed { index, linearLayout ->
            linearLayout.setOnClickListener {
                if (arrayText[index].tag == "*") {
                    setGameOver()
                } else {
                    openTiles(index, arrayBoard.size)
                }
                it.setOnClickListener {  }
            }
        }
    }

    private fun openTiles(position : Int, boardSize : Int) {
        rippleBox(position, boardSize,1)
        rippleBox(position, boardSize, -1)

        if (checkTilesLeft()) {
            textView21.text = "You Win"
            button16.text = "B)"
            arrayText.map { it.setOnClickListener {  } }
        }
    }

    private fun rippleBox (position : Int, boardSize : Int, condition : Int) {
        if (arrayText[position].tag.toString() != "*") {
            if (arrayText[position].tag.toString().toInt() > 0) {
                displayTextTiles(position)
            } else {
                displayTextTiles(position)
                if (position + condition in 0 until boardSize) rippleBox(position + condition, boardSize, condition)
            }
        }
    }

    private fun displayTextTiles (position : Int) {
        arrayText[position].text = arrayText[position].tag.toString()
        arrayBoard[position].setOnClickListener {  }
    }


    private fun checkTilesLeft (): Boolean {
        var isWin = true
        arrayText.mapIndexed { index, textView ->
            if (!arrayBom.contains(index)){
                if (textView.text.toString().isBlank()) isWin = false
            }
        }
        return isWin
    }

    private fun createRandomBom(bomLength: Int, boardLength: Int) : MutableList<Int> {
        val result = arrayListOf<Int>()
        var tempBomLength = bomLength

        while (tempBomLength > 0) {
            val tempBom = getRandom(boardLength)
            if (!result.contains(tempBom)) {
                result.add(tempBom)
                tempBomLength--
            }
        }
        return result
    }

    private fun getRandom(number: Int) : Int {
        random = Random()
        return random.nextInt(number)
    }

    private fun setGameOver () {
        arrayBom.forEach {
            arrayText[it].text = "*"
        }
        arrayBoard.map { it.setOnClickListener {  } }
        arrayText.map { it.setOnClickListener {  } }
        textView21.text = "Game Over"
        button16.text = ":("
    }
}
