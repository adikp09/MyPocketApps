package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        updateUI("")
    }


    private val operationList: MutableList<String> = arrayListOf()
    private val numCache: MutableList<String> = arrayListOf()

    private fun extractString (items: List<String>, connect: String = ""): String {
        if (items.isEmpty()) return ""
        return items.reduce { acc, s ->  acc + connect + s}
    }

    private fun updateUI (mainUIString: String) {
        val calculationString = extractString(operationList, "")
        var calculationTxtView = numberDisp
        calculationTxtView.text = calculationString

        val ans = display
        ans.text = mainUIString
    }

    fun numberSmash (view: View) {
        val button = view as Button
        val numString = button.text

        numCache.add (numString.toString())
        val text = extractString(numCache)
        updateUI(text)
    }

    fun operatorSmash (view: View) {
        val button = view as Button
        if (numCache.isEmpty()) return

        operationList.add(extractString(numCache))
        numCache.clear()
        operationList.add(button.text.toString())
        updateUI(button.text.toString())
    }

    private fun clearCache () {
        operationList.clear()
        numCache.clear()
    }

    fun allClear (view: View) {
        clearCache()
        updateUI("")
    }

    fun equalSmash (view: View) {
        if (numCache.isNotEmpty()) {
            operationList.add (extractString(numCache))
            numCache.clear()

            val answer = calculate(operationList)

            updateUI("=" + answer.toString())
            clearCache()
        }
    }

    private fun calculate (calculationList: List<String>): Int {
        var currentOp = ""
        var currentNumber = 0

        calculationList.forEach { token ->

            when {
                token == "+"
                        || token == "-"
                        || token == "X"
                        || token == "/" -> currentOp = token

                currentOp == "-" -> currentNumber -= token.toInt()
                currentOp == "/" -> currentNumber /= token.toInt()
                currentOp == "X" -> currentNumber *= token.toInt()

                else -> currentNumber += token.toInt()
            }

        }

        return currentNumber
    }
}

