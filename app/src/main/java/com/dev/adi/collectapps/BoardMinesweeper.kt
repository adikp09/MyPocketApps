package com.dev.adi.collectapps

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class BoardMinesweeper (val context: Context) {
    fun createBoard (): LinearLayout {
        val lineChild = LinearLayout(context)
        val params = LinearLayout.LayoutParams(110, 110)
        params.setMargins(2, 2, 2, 2)

        lineChild.layoutParams = params
        lineChild.orientation = LinearLayout.HORIZONTAL
        lineChild.gravity = Gravity.CENTER

        val border = GradientDrawable()
        border.setColor(-0x1) //white background
        border.setStroke(1, -0x1000000) //black border with full opacity

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) lineChild.setBackgroundDrawable(border)
        else lineChild.background = border
        lineChild.setPadding(5, 5, 5, 5)

        return lineChild
    }

    fun createTextView (): TextView {
        val textView = TextView(context)
        textView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        textView.textSize = 16F
        textView.tag = "0"
        textView.gravity = Gravity.CENTER
        return textView
    }
}
