package com.dev.adi.collectapps.singleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_multi_calculator.*

class MultiCalculator : AppCompatActivity() {

    private val listMenu = arrayOf("Decimal", "Binary", "Hexadecimal")
    private val listOperation = arrayOf("+", "-", "x", "/")
    private val listDigits = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_calculator)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listMenu)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinners.adapter = adapter

        val adapterOperation = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOperation)
        adapterOperation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        operationSpinners.adapter = adapterOperation



        btn_calculate.setOnClickListener {
            var textvalueOne = editText11.text.toString()
            var textvalueTwo = editText12.text.toString()
            var operation = operationSpinners.selectedItem.toString()
            when {
                spinners.selectedItem.toString() === "Decimal" -> {
                    var valueOneInt = textvalueOne.toInt()
                    var valueTwoInt = textvalueTwo.toInt()
                    var result = calculate(valueOneInt, valueTwoInt, operation)
                    printResult(result)
                }
                spinners.selectedItem.toString() === "Binary" -> {
                    var valueOneInt = binaryToDecimal(textvalueOne)
                    var valueTwoInt = binaryToDecimal(textvalueTwo)
                    var result = calculate(valueOneInt, valueTwoInt, operation)
                    printResult(result)

                }
                spinners.selectedItem.toString() === "Hexadecimal" -> {
                    var valueOneInt = hexToDecimal(textvalueOne)
                    var valueTwoInt = hexToDecimal(textvalueTwo)
                    var result = calculate(valueOneInt, valueTwoInt, operation)
                    printResult(result)
                }
                else -> {

                }
            }
        }
    }

    fun calculate (valueA : Int, valueB : Int, operation : String) : Int {
        var result = 0
        when {
            operation === "+" -> {
                result = valueA + valueB
            }
            operation === "-" -> {
                result = valueA - valueB
            }
            operation === "x" -> {
                result = valueA / valueB
            }
            operation === "/" -> {
                result = valueA / valueB
            }
        }
        return result
    }

    private fun decimalToBinary(decimalNumber: Int, binaryString: String = "") : String {
        while (decimalNumber > 0) {
            val temp = "$binaryString${decimalNumber%2}"
            return decimalToBinary(decimalNumber/2, temp)
        }
        return binaryString.reversed()
    }

    private fun binaryToDecimal(binaryNumber : String) : Int {
        var sum = 0
        binaryNumber.reversed().forEachIndexed { k, v ->
            sum += v.toString().toInt() * pow(2, k)
        }
        return sum
    }

    private fun hexToDecimal(bytes: String): Int {
        var result = 0
        val hexArray = bytes.toCharArray()
        hexArray.reverse()
        hexArray.forEachIndexed { index, c ->
            result += listDigits.indexOf(c.toString()) * (Math.pow(16.toDouble(), index.toDouble())).toInt()
        }

        return result
    }

    private fun decimalToHex(decimalNumber: Int): String {
        var result = ""
        var forProcessing = decimalNumber
        while (forProcessing > 0) {
            val mod = forProcessing % 16
            result += listDigits[mod]
            forProcessing /= 16
        }
        return result.reversed()
    }

    private fun printResult (result : Int) {
        tv_result.text = ""
        tv_result.append("Your result\n")
        tv_result.append("Decimal : $result \n")
        tv_result.append("Binary : ${decimalToBinary(result)} \n")
        tv_result.append("Hexadecimal: ${decimalToHex(result)} \n")

    }

    private fun pow(base: Int, exponent: Int) = Math.pow(base.toDouble(), exponent.toDouble()).toInt()
}
