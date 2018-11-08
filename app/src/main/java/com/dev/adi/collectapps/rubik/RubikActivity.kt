package com.dev.adi.collectapps.rubik

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Toast
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_rubik.*
import java.util.*


class RubikActivity : AppCompatActivity() {

    private val tiles = arrayListOf<MutableList<Tile>>()
    private val tilesAnswer = arrayListOf<MutableList<Tile>>()
    private val mainBoard = arrayListOf<MutableList<LinearLayout>>()
    private val answerBoard = arrayListOf<MutableList<LinearLayout>>()
    val color = arrayOf("#FF0000", "#008000", "#0000FF", "#000000", "#FFFF00", "#FFC0CB")
    private val board = 5
    private val boardAnswer = 3
    private var random : Random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rubik)
        var x = 0
        var y = 0
        color.forEachIndexed { index, colorString ->
            if (index != color.size-1) {
                x = 0
                val temp = arrayListOf<Tile>()
                while (temp.size < board-1) {
                    temp.add(Tile(x, y, false, colorString))
                    x++
                }
                tiles.add(temp)
                y++
            } else {
                y = 0
                x = board -1
                tiles.forEachIndexed { indexTiles, it ->
                    if (indexTiles < board-1) {
                        it.add(Tile(x, y, false, colorString))
                        y++
                    }
                }
                tiles[tiles.size-1].add(Tile(x, tiles.size - 1,true, "#FFFFFF"))
            }
        }
        renderBoards(line_parent, mainBoard, tiles)
        onPerfomClick()
        renderAnswer()
        renderBoards(line_answer, answerBoard, tilesAnswer)
    }

    private fun renderBoards(lineParent: LinearLayout, board: ArrayList<MutableList<LinearLayout>>, tiles: ArrayList<MutableList<Tile>>) {
        lineParent.removeAllViews()
        board.clear()
        tiles.forEach { it ->
            val line = LinearLayout(this)
            line.orientation = LinearLayout.HORIZONTAL
            line.gravity = Gravity.CENTER
            val tempLine =arrayListOf<LinearLayout>()
            it.forEachIndexed { indexY, tile ->
                val lineTile = LinearLayout(this)
                val params = LinearLayout.LayoutParams(140, 140)
                params.setMargins(2,2,2,2)
                lineTile.layoutParams = params
                val border = GradientDrawable()
                border.setColor(Color.parseColor(tile.color))
                border.setStroke(1, -0x1000000)
                lineTile.background = border
                lineTile.setPadding(5,5,5,5)
                line.addView(lineTile)
                tempLine.add(lineTile)
            }
            board.add(tempLine)
            lineParent.addView(line)
        }
    }

    fun checkBoard (): Boolean {
        var isWin = true
        tilesAnswer.forEachIndexed { y, mutableList ->
            mutableList.forEachIndexed { x, tile ->
                if (tilesAnswer[y][x].color != tiles[y+1][x+1].color)
                    isWin = false
            }
        }
        return isWin
    }

    private fun onPerfomClick() {
        var isEmptyTile : Tile? = null
        tiles.forEach { data ->
            data.forEach {
                if (it.isEmpty) {
                    isEmptyTile = it
                }
            }
        }

        isEmptyTile?.let {
            if (it.y - 1 > -1) {
                mainBoard[it.y - 1][it.x].setOnClickListener {itView ->
                    if (it.y - 1 > -1) mainBoard[it.y - 1][it.x].setOnClickListener { null }
                    if (it.x - 1 > -1) mainBoard[it.y][it.x - 1].setOnClickListener { null }
                    if (it.y + 1 < board) mainBoard[it.y + 1][it.x].setOnClickListener { null }
                    if (it.x + 1 < board) mainBoard[it.y][it.x + 1].setOnClickListener { null }
                    action(it, tiles[it.y - 1][it.x])
                }
            }
            if (it.x - 1 > -1) {
                mainBoard[it.y][it.x - 1].setOnClickListener {itView ->
                    if (it.y - 1 > -1) mainBoard[it.y - 1][it.x].setOnClickListener { null }
                    if (it.x - 1 > -1) mainBoard[it.y][it.x - 1].setOnClickListener { null }
                    if (it.y + 1 < board) mainBoard[it.y + 1][it.x].setOnClickListener { null }
                    if (it.x + 1 < board) mainBoard[it.y][it.x + 1].setOnClickListener { null }
                    action(it, tiles[it.y][it.x - 1])
                }
            }
            if (it.y + 1 < board) {
                mainBoard[it.y + 1][it.x].setOnClickListener {itView ->
                    if (it.y - 1 > -1) mainBoard[it.y - 1][it.x].setOnClickListener { null }
                    if (it.x - 1 > -1) mainBoard[it.y][it.x - 1].setOnClickListener { null }
                    if (it.y + 1 < board) mainBoard[it.y + 1][it.x].setOnClickListener { null }
                    if (it.x + 1 < board) mainBoard[it.y][it.x + 1].setOnClickListener { null }
                    action(it, tiles[it.y + 1][it.x])
                }
            }
            if (it.x + 1 < board) {
                mainBoard[it.y][it.x + 1].setOnClickListener {itView ->
                    if (it.y - 1 > -1) mainBoard[it.y - 1][it.x].setOnClickListener { null }
                    if (it.x - 1 > -1) mainBoard[it.y][it.x - 1].setOnClickListener { null }
                    if (it.y + 1 < board) mainBoard[it.y + 1][it.x].setOnClickListener { null }
                    if (it.x + 1 < board) mainBoard[it.y][it.x + 1].setOnClickListener { null }
                    action(it, tiles[it.y][it.x + 1])
                }
            }
        }
    }

    private fun action(from: Tile, to: Tile) {
        changeColor(from.color, to.x, to.y)
        changeColor(to.color, from.x, from.y)

        tiles[from.y][from.x] = Tile(from.x, from.y, to.isEmpty, to.color)
        tiles[to.y][to.x] = Tile(to.x, to.y, from.isEmpty, from.color)

        if (checkBoard()) {
            Toast.makeText(this,"YOU WIN", Toast.LENGTH_LONG).show()
        }

        onPerfomClick()
    }

    private fun changeColor (color : String, x: Int, y : Int) {
        val border = GradientDrawable()
        border.setColor(Color.parseColor(color))
        border.setStroke(1, -0x1000000)
        mainBoard[y][x].background = border
    }


    private fun randomNumber(length: Int) : Int {
        return random.nextInt(length)
    }

    private fun renderAnswer () {
        val bag = arrayListOf<Tile>()
        tiles.forEach { data ->
            data.forEach {
                if (!it.isEmpty) {
                    bag.add(it)
                }
            }
        }

        e("size", bag.size.toString())

        var x = 0
        var y = 0
        while (y < boardAnswer) {
            x = 0
            val temp = arrayListOf<Tile>()
            while (x < boardAnswer) {

                var randomIndex = randomNumber(bag.size)
                temp.add(Tile(x, y, false, bag[randomIndex].color))
                bag.removeAt(randomIndex)
                x++
            }
            tilesAnswer.add(temp)
            y++
        }
        e("meme", tilesAnswer.toString())
    }
}
