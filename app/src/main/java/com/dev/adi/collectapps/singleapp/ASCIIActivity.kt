package com.dev.adi.collectapps.singleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
import android.view.inputmethod.EditorInfo
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_ascii.*

class ASCIIActivity : AppCompatActivity() {

    var libraryAscii = arrayOf(' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '\'', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ascii)

        et_value.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                tv_result.text = ""
                val charset = Charsets.UTF_8
                val byteArray = et_value.text.toString().toByteArray(charset)

                tv_result.append("Your result\n")
                tv_result.append("Decimal : ${byteArray.mapIndexed { _, c -> c }} \n")
                tv_result.append("Binary : ${byteArray.mapIndexed { _, c -> decimalToBinary(c.toInt())}} \n")

                manualAscii(et_value.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        editText13.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val tempValue = editText13.text.toString().toInt()

                textView30.text = ""
                textView30.append("Your result\n")
                textView30.append("Input : $tempValue \n")
                textView30.append("Input Binary : ${decimalToBinary(tempValue)} \n")

                textView30.append("\n")
                textView30.append("----Bit Shifts----\n")

                textView30.append("Output left : ${tempValue shr 1} \n")
                textView30.append("Output right : ${tempValue shl 1} \n")

                var manualLeft = manualLeftShif(decimalToBinary(tempValue))
                var manualRight = manualRightShif(decimalToBinary(tempValue))

                textView30.append("\n")
                textView30.append("----Manual Shifts----\n")
                textView30.append("Output left : ${binaryToDecimal(manualLeft)} \n")
                textView30.append("Output right : ${binaryToDecimal(manualRight)} \n")

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        button17.setOnClickListener {
            val emojiInt = editText14.text.toString()
            textView31.text = ""
            emojiInt.forEachIndexed { index, c ->
                val unicode = emojiInt.codePointAt(index)
                val unihex = unicode.toString(16)
                val sb = StringBuilder().appendCodePoint(unicode).toString()
                textView31.append("$unicode, $unihex, $sb\n")

            }
        }
    }

    private fun manualAscii (value : String) {
        var temp = value.toCharArray()
        val arr = libraryAscii.toList()

        temp.map { it ->
            var temps = arr.indexOf(it)+32
            e("decimal", temps.toString())

        }

        temp.map { it ->
            var temps = arr.indexOf(it)+32
            e("Binary", decimalToBinary(temps))

        }
    }

    private fun decimalToBinary (decimalNumber : Int, binaryString : String = ""): String {
        while (decimalNumber > 0) {
            val mod = "$binaryString${decimalNumber%2}"
            return decimalToBinary(decimalNumber/2 , mod)
        }
        return binaryString.reversed()
    }

    private fun manualRightShif (value : String): String {
        return value+0
    }

    private fun manualLeftShif (value : String): String {
        return "$value".substring(0, value.length-1)
    }

    private fun binaryToDecimal(binaryNumber : String) : Int {
        var sum = 0
        binaryNumber.reversed().forEachIndexed { k, v ->
            sum += v.toString().toInt() * pow(2, k)
        }
        return sum
    }

    private fun pow(base: Int, exponent: Int) = Math.pow(base.toDouble(), exponent.toDouble()).toInt()

//                    val sb = StringBuilder().appendCodePoint(unicode).toString()
//    Log.e("sb", sb)

}
