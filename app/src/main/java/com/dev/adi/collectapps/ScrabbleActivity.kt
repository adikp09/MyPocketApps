package com.dev.adi.collectapps

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_scrabble.*
import java.util.*


class ScrabbleActivity : AppCompatActivity() {

    private lateinit var random: Random
    private val wordList: MutableList<DataWord> = arrayListOf()

    private val playerOneWord: MutableList<DataWord> = arrayListOf()
    private val tempPlayerOneWord: MutableList<DataWord> = arrayListOf()
    private val playerOneAnsw: MutableList<Coordinate> = arrayListOf()

    private val playerTwoWord: MutableList<DataWord> = arrayListOf()
    private val tempPlayerTwoWord: MutableList<DataWord> = arrayListOf()
    private val playerTwoAnsw: MutableList<Coordinate> = arrayListOf()

    private var bag = arrayOfNulls<String>(100)
    private var xyLine = arrayOfNulls<MutableList<Button>>(15)
    private var xyButton = arrayOfNulls<Button>(15)

    private var firstTurn = true
    private var turn = 0
    private var skip = 0
    private var orientation = 0
    private var tempCoordinate: MutableList<Coordinate> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrabble)
        turn = 1
        wordList.add(DataWord("-", 0, 2))
        wordList.add(DataWord("A", 1, 19))
        wordList.add(DataWord("B", 5, 4))
        wordList.add(DataWord("C", 8, 3))
        wordList.add(DataWord("D", 3, 4))
        wordList.add(DataWord("E", 1, 8))
        wordList.add(DataWord("F", 5, 1))
        wordList.add(DataWord("G", 3, 3))
        wordList.add(DataWord("H", 4, 2))
        wordList.add(DataWord("I", 1, 8))
        wordList.add(DataWord("J", 10, 1))
        wordList.add(DataWord("K", 2, 3))
        wordList.add(DataWord("L", 4, 3))
        wordList.add(DataWord("M", 2, 3))
        wordList.add(DataWord("N", 1, 9))
        wordList.add(DataWord("O", 1, 3))
        wordList.add(DataWord("P", 4, 2))
        wordList.add(DataWord("R", 1, 4))
        wordList.add(DataWord("S", 1, 3))
        wordList.add(DataWord("T", 1, 5))
        wordList.add(DataWord("U", 1, 5))
        wordList.add(DataWord("V", 8, 1))
        wordList.add(DataWord("W", 5, 1))
        wordList.add(DataWord("Y", 5, 2))
        wordList.add(DataWord("Z", 10, 1))

        tv_player_turns.text = "Player turn: " + turn

        playerOneWord.addAll(wordList)
        playerTwoWord.addAll(wordList)
        tempPlayerOneWord.addAll(playerGetWord(turn))
        tempPlayerTwoWord.addAll(playerGetWord(turn))

        var btnIndex = 0
        val lineFirst = LinearLayout(this)
        lineFirst.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        var i = 0
        while (i < 16) {
            val btnFirst = Button(this)
            btnFirst.layoutParams = LinearLayout.LayoutParams(100, 100)
            btnFirst.text = (i).toString()
            btnFirst.textSize = 12F
            lineFirst.addView(btnFirst)
            i++
        }
        line_parent?.addView(lineFirst)

        xyLine.mapIndexed { indexLine, lineButton ->
            val line = LinearLayout(this)
            line.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val btnFirst = Button(this)
            btnFirst.layoutParams = LinearLayout.LayoutParams(100, 100)
            btnFirst.text = (indexLine + 1).toString()
            btnFirst.textSize = 12F
            line.addView(btnFirst)

            val arrayButton: MutableList<Button> = arrayListOf()
            xyButton.mapIndexed { indexButton, button ->
                val btn = Button(this)
                btn.layoutParams = LinearLayout.LayoutParams(100, 100)
                btn.id = btnIndex; btnIndex++
                btn.textSize = 12F
                btn.setOnClickListener {
                    val mIntent = Intent(this, ScrabbleDetail::class.java)
                    mIntent.putExtra("playerLatter", if (turn == 1) getWordParam(tempPlayerOneWord) else getWordParam(tempPlayerTwoWord))
                    mIntent.putExtra("indexButton", indexButton)
                    mIntent.putExtra("indexLine", indexLine)
                    mIntent.putExtra("turn", turn)
                    startActivityForResult(mIntent, 1002)
                }
                line.addView(btn)
                arrayButton.add(btn)
            }
            line_parent?.addView(line)
            xyLine[indexLine] = arrayButton
        }

        btn_done.setOnClickListener {
            tempCoordinate.clear()
            if (turn == 1) {
                playerOneAnsw.map {
                    if (it.x == 7 && it.y == 7) {
                        firstTurn = false
                    }
                }
            } else {
                playerTwoAnsw.map {
                    if (it.x == 7 && it.y == 7) {
                        firstTurn = false
                    }
                }
            }
            if (!firstTurn) {
                if (playerOneAnsw.size > 0 || playerTwoAnsw.size > 0) {
                    if (playerOneAnsw.size > 1 && turn == 1) {
                        orientation = getOrientation(playerOneAnsw)
                        val kata: MutableList<String> = arrayListOf()
                        var poin = 0
                        if (orientation == 1) {
                            playerOneAnsw.forEachIndexed { index, coor ->
                                if (!kata.contains(getLetter(playerOneAnsw[index],1))) {
                                    poin += getValue(getLetter(playerOneAnsw[index],1), tempCoordinate)
                                    kata.add(getLetter(playerOneAnsw[index],1))
                                }
                            }
                            playerOneAnsw.forEachIndexed { index, coor ->
                                if (getLetter(playerOneAnsw[index], 2).length > 1) {
                                    poin += getValue(getLetter(playerOneAnsw[index],2), tempCoordinate)
                                    kata.add(getLetter(playerOneAnsw[index], 2))
                                }
                            }
                        } else if (orientation == 2) {
                            playerOneAnsw.forEachIndexed { index, coor ->
                                if (!kata.contains(getLetter(playerOneAnsw[index], 2))) {
                                    poin += getValue(getLetter(playerOneAnsw[index],2), tempCoordinate);
                                    kata.add(getLetter(playerOneAnsw[index], 2))
                                }
                            }
                            playerOneAnsw.forEachIndexed { index, coor ->
                                if (getLetter(playerOneAnsw[index], 1).length > 1) {
                                    poin += getValue(getLetter(playerOneAnsw[index],1), tempCoordinate);
                                    kata.add(getLetter(playerOneAnsw[index], 1))
                                }
                            }
                        }
                        showDialogDone(activity = this, message = "Correct ${kata.map { it }} ?", poin = poin)
                    } else if (playerTwoAnsw.size > 1 && turn == 2) {
                        orientation = getOrientation(playerTwoAnsw)
                        val kata: MutableList<String> = arrayListOf()
                        var poin = 0
                        if (orientation == 1) {
                            playerTwoAnsw.forEachIndexed { index, coor ->
                                if (!kata.contains(getLetter(playerTwoAnsw[index], 1))) {
                                    poin += getValue(getLetter(playerTwoAnsw[index],1), tempCoordinate)
                                    kata.add(getLetter(playerTwoAnsw[index], 1))
                                }
                            }
                            playerTwoAnsw.forEachIndexed { index, coor ->
                                if (getLetter(playerTwoAnsw[index], 2).length > 1) {
                                    poin += getValue(getLetter(playerTwoAnsw[index],2), tempCoordinate)
                                    kata.add(getLetter(playerTwoAnsw[index], 2))
                                }
                            }
                        } else if (orientation == 2) {
                            playerTwoAnsw.forEachIndexed { index, coor ->
                                if (!kata.contains(getLetter(playerTwoAnsw[index], 2))) {
                                    poin += getValue(getLetter(playerTwoAnsw[index],2), tempCoordinate)
                                    kata.add(getLetter(playerTwoAnsw[index], 2))
                                }
                            }
                            playerTwoAnsw.forEachIndexed { index, coor ->
                                if (getLetter(playerTwoAnsw[index], 1).length > 1) {
                                    poin += getValue(getLetter(playerTwoAnsw[index],1), tempCoordinate)
                                    kata.add(getLetter(playerTwoAnsw[index], 1))
                                }
                            }
                        }
                        showDialogDone(activity = this, message = "Correct ${kata.map { it }} ?", poin = poin)
                    } else {
                        val kata: MutableList<String> = arrayListOf()
                        var poin = 0
                        if (turn == 1) {
                            playerOneAnsw.forEachIndexed { index, coor ->
                                if (getLetter(playerOneAnsw[index], 1).length > 1) {
                                    poin += getValue(getLetter(playerOneAnsw[index],1), tempCoordinate)
                                    kata.add(getLetter(playerOneAnsw[index], 1))
                                }
                            }
                            playerOneAnsw.forEachIndexed { index, coor ->
                                if (getLetter(playerOneAnsw[index], 2).length > 1) {
                                    poin += getValue(getLetter(playerOneAnsw[index],2), tempCoordinate)
                                    kata.add(getLetter(playerOneAnsw[index], 2))
                                }
                            }
                        } else if (turn == 2){
                            playerTwoAnsw.forEachIndexed { index, coor ->
                                if (getLetter(playerTwoAnsw[index], 2).length > 1) {
                                    poin += getValue(getLetter(playerTwoAnsw[index],2), tempCoordinate)
                                    kata.add(getLetter(playerTwoAnsw[index], 2))
                                }
                            }
                            playerTwoAnsw.forEachIndexed { index, coor ->
                                if (getLetter(playerTwoAnsw[index], 1).length > 1) {
                                    poin += getValue(getLetter(playerTwoAnsw[index],1), tempCoordinate)
                                    kata.add(getLetter(playerTwoAnsw[index], 1))
                                }
                            }
                        }

                        showDialogDone(activity = this, message = "Correct ${kata.map { it }} ?", poin = poin)
                    }
                } else {
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please, start from the middle at [8,8]", Toast.LENGTH_SHORT).show()
            }
        }

        btn_skip.setOnClickListener {
            if (turn == 1) {
                turn = 2
                tempPlayerOneWord.mapIndexed { index, letter ->
                    tempPlayerOneWord[index] = DataWord(letter.word, letter.poin, letter.count, false)
                }
                playerOneAnsw.clear()
            } else if (turn == 2) {
                turn = 1
                tempPlayerTwoWord.mapIndexed { index, letter ->
                    tempPlayerTwoWord[index] = DataWord(letter.word, letter.poin, letter.count, false)
                }
                playerTwoAnsw.clear()
            }
            skip++
            orientation = 0
            tempCoordinate.clear()
            if (skip > 1) {
                val builder = AlertDialog.Builder(this)

                if (title != null) builder.setTitle(title)

                builder.setMessage("Game Over")
                builder.setPositiveButton("OK") { dialog, id ->
                    dialog.dismiss()
                    finish()
                }
                builder.show()
            }
            tv_player_turns.text = "Player turn: " + turn
        }
    }

    private fun addBag(listStock: MutableList<DataWord>): MutableList<String> {
        val result: MutableList<String> = arrayListOf()
        listStock.map {
            var i = 0
            while (i < it.count){
                result.add(it.word)
                i++
            }
        }

        return result
    }

    private fun getRandom(length: Int) : Int {
        random = Random()
        return random.nextInt(length)
    }

    private fun getWordParam(cardOnHand: MutableList<DataWord> = arrayListOf()): Array<String?> {
        val word = arrayOfNulls<String>(7)
        cardOnHand.mapIndexed { index, words ->
            if (!words.isShow) word[index] = words.word
        }
        return word
    }

    private fun playerGetWord(type : Int): MutableList<DataWord> {
        val tempCard: MutableList<DataWord> = arrayListOf()
        if (type == 1) {
            val bag = addBag(playerOneWord)
            while (tempCard.size < 7) {
                val rand = getRandom(bag.size)
                var position: Int? = null
                playerOneWord.mapIndexed { index, data ->
                    if (data.word == bag[rand]) position = index
                }
                if (playerOneWord[position ?: 0].count > 0) {
                    tempCard.add(wordList[position ?: 0])
                    playerOneWord[position ?: 0] = DataWord(wordList[position
                            ?: 0].word, wordList[position ?: 0].poin, playerOneWord[position
                            ?: 0].count - 1)
                }
            }
        } else {
            val bag = addBag(playerTwoWord)
            while (tempCard.size < 7) {
                val rand = getRandom(bag.size)
                var position: Int? = null
                playerTwoWord.mapIndexed { index, data ->
                    if (data.word == bag[rand]) position = index
                }
                if (playerTwoWord[position ?: 0].count > 0) {
                    tempCard.add(wordList[position ?: 0])
                    playerTwoWord[position ?: 0] = DataWord(wordList[position
                            ?: 0].word, wordList[position ?: 0].poin, playerTwoWord[position
                            ?: 0].count - 1)
                }
            }
        }
        return tempCard
    }

    private fun updateWordPlayer(type : Int): MutableList<DataWord> {
        val tempCard: MutableList<DataWord> = arrayListOf()
        if (type === 1) {
            val tempBag = addBag(playerOneWord)
            tempPlayerOneWord.map {
                if (!it.isShow) tempCard.add(it)
            }
            while (tempCard.size < 7) {
                val rand = getRandom(bag.size)
                var position: Int? = null
                playerOneWord.mapIndexed { index, words ->
                    if (words.word == tempBag[rand]) position = index
                }
                if (playerOneWord[position?:0].count > 0) {
                    tempCard.add(wordList[position?:0])
                    playerOneWord[position?:0] = DataWord(wordList[position?:0].word, wordList[position?:0].poin, playerOneWord[position?:0].count-1)
                }
            }
        } else {
            val tempBag = addBag(playerOneWord)
            tempPlayerTwoWord.map {
                if (!it.isShow) tempCard.add(it)
            }
            while (tempCard.size < 7) {
                val rand = getRandom(bag.size)
                var position: Int? = null
                wordList.mapIndexed { index, words ->
                    if (words.word == tempBag[rand]) position = index
                }
                if (playerTwoWord[position?:0].count > 0) {
                    tempCard.add(wordList[position?:0])
                    playerTwoWord[position?:0] = DataWord(wordList[position?:0].word, wordList[position?:0].poin, playerTwoWord[position?:0].count-1)
                }
            }
        }
        return tempCard
    }

    private fun getLetter(coordinate: Coordinate, orientation: Int): String {
        var result = ""
        var terkecil = 15
        var terbesar = 0

        if (orientation == 1) {
            var i = coordinate.y
            while (i >= 0) {
                if (xyLine[i]!![coordinate.x].text.isNotEmpty()) terkecil = i
                else break
                i--
            }
            i = coordinate.y
            while (i < 15) {
                if (xyLine[i]!![coordinate.x].text.isNotEmpty()) terbesar = i
                else break
                i++
            }

            i = terkecil
            while (i <= terbesar) {
                result += xyLine[i]!![coordinate.x].text
                tempCoordinate.add(Coordinate(coordinate.x, i))
                i++
            }
        } else if (orientation == 2) {
            var i = coordinate.x
            while (i >= 0) {
                if (xyLine[coordinate.y]!![i].text.isNotEmpty()) terkecil = i
                else break
                i--
            }
            i = coordinate.x
            while (i < 15) {
                if (xyLine[coordinate.y]!![i].text.isNotEmpty()) terbesar = i
                else break
                i++
            }

            i = terkecil
            while (i <= terbesar) {
                result += xyLine[coordinate.y]!![i].text
                i++
            }
        }

        return result
    }

    private fun getPoin(char: String) : DataWord {
        var iLetter = 0

        wordList.mapIndexed { index, letter ->
            if (char.capitalize() == letter.word.capitalize()) {
                iLetter = index
            }
        }

        return wordList[iLetter]
    }

    private fun getValue(kata: String, listCoorAns: MutableList<Coordinate>): Int {
        var value = 0
        val letter = kata.toCharArray()
        letter.mapIndexed{ _, char ->
            val letter = getPoin(char.toString())
            value += letter.poin
        }
        return value
    }

    private fun getOrientation(listCoorAns: MutableList<Coordinate>) : Int {
        var orientation = 0
        if (listCoorAns[0].x == listCoorAns[1].x) orientation = 1
        else if (listCoorAns[0].y == listCoorAns[1].y) orientation = 2
        return orientation
    }

    private fun showDialogDone(activity: Activity, title: String? = null, message: CharSequence, poin: Int) {
        val builder = AlertDialog.Builder(activity)

        if (title != null) builder.setTitle(title)

        builder.setMessage(message)
        builder.setPositiveButton("Yes") { dialog, id ->
            if (turn == 1) {
                turn = 2
                val tempPoin = textView10.text.toString().toInt()
                textView10.text = (tempPoin+poin).toString()
                val temp = updateWordPlayer(2)
                tempPlayerOneWord.clear()
                tempPlayerOneWord.addAll(temp)
                playerOneAnsw.clear()
                tv_player_turns.text = "Player turn: " + turn
            } else if (turn == 2) {
                turn = 1
                val tempPoin = textView11.text.toString().toInt()
                textView11.text = (tempPoin+poin).toString()
                tempPlayerTwoWord.clear()
                val temp = updateWordPlayer(1)
                tempPlayerTwoWord.clear()
                tempPlayerTwoWord.addAll(temp)
                playerTwoAnsw.clear()
                tv_player_turns.text = "Player turn: " + turn
            }

            skip = 0
            xyLine.mapIndexed { _, mutableList ->
                mutableList?.mapIndexed { _, button ->
                    if (button.text.toString().isNotEmpty()) {
                        button.isEnabled = false
                    }
                }
            }
            dialog.dismiss() }
        builder.setNegativeButton("No") { dialog, id ->
            dialog.dismiss() }
        builder.show()
    }

    class DataWord (val word: String, val poin: Int, val count: Int, val isShow : Boolean = false)

    class Coordinate (val x : Int, val y : Int)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            var extrasTurn = data?.extras?.getInt("turn")
            var extrasIndexLine = data?.extras?.getInt("indexLine")
            var extrasIndexButton = data?.extras?.getInt("indexButton")
            var extrasWordPick = data?.extras?.getString("wordPick")

            if (extrasTurn == 1) {
                when {
                    xyLine[extrasIndexLine?:0]!![extrasIndexButton?:0].text.isNullOrEmpty() -> {
                        var position = 0
                        tempPlayerOneWord.mapIndexed { index, words ->
                            if (extrasWordPick == words.word && !words.isShow) position = index
                        }
                        tempPlayerOneWord[position] = DataWord(tempPlayerOneWord[position].word, tempPlayerOneWord[position].poin, tempPlayerOneWord[position].count, true)
                        xyLine[extrasIndexLine?:0]!![extrasIndexButton?:0].text = extrasWordPick
                        playerOneAnsw.add(Coordinate(extrasIndexButton?:0, extrasIndexLine?:0))
                    }
                    else -> {
                        var position = 0
                        tempPlayerOneWord.mapIndexed { index, words ->
                            if (xyLine[extrasIndexLine?:0]!![extrasIndexButton?:0].text == words.word && words.isShow) position = index
                        }
                        var position2 = 0
                        tempPlayerOneWord.mapIndexed { index, words ->
                            if (extrasWordPick == words.word && !words.isShow) position2 = index
                        }
                        tempPlayerOneWord[position] = DataWord(tempPlayerOneWord[position].word, tempPlayerOneWord[position].poin, tempPlayerOneWord[position].count, false)
                        tempPlayerOneWord[position2] = DataWord(tempPlayerOneWord[position2].word, tempPlayerOneWord[position2].poin, tempPlayerOneWord[position2].count, true)
                        xyLine[extrasIndexLine?:0]!![extrasIndexButton?:0].text = extrasWordPick
                    }
                }
            } else if (extrasTurn == 2) {
                when {
                    xyLine[extrasIndexLine?:0]!![extrasIndexButton?:0].text.isNullOrEmpty() -> {
                        var position = 0
                        tempPlayerTwoWord.mapIndexed { index, words ->
                            if (extrasWordPick == words.word && !words.isShow) position = index
                        }
                        tempPlayerTwoWord[position] = DataWord(tempPlayerTwoWord[position].word, tempPlayerTwoWord[position].poin, tempPlayerTwoWord[position].count, true)
                        xyLine[extrasIndexLine?:0]!![extrasIndexButton?:0].text = extrasWordPick
                        playerTwoAnsw.add(Coordinate(extrasIndexButton?:0, extrasIndexLine?:0))
                    }
                    else -> {
                        var position = 0
                        tempPlayerTwoWord.mapIndexed { index, words ->
                            if (xyLine[extrasIndexLine?:0]!![extrasIndexButton?:0].text == words.word && words.isShow) position = index
                        }
                        var position2 = 0
                        tempPlayerTwoWord.mapIndexed { index, words ->
                            if (extrasWordPick == words.word && !words.isShow) position2 = index
                        }
                        tempPlayerTwoWord[position] = DataWord(tempPlayerTwoWord[position].word, tempPlayerTwoWord[position].poin, tempPlayerTwoWord[position].count, false)
                        tempPlayerTwoWord[position2] = DataWord(tempPlayerTwoWord[position2].word, tempPlayerTwoWord[position2].poin, tempPlayerTwoWord[position2].count, true)
                        xyLine[extrasIndexLine?:0]!![extrasIndexButton?:0].text = extrasWordPick
                    }
                }
            }
        }

    }

