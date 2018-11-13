package com.dev.adi.collectapps.singleapp

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_calendar.*
import java.text.SimpleDateFormat
import java.util.*


class CalendarActivity : AppCompatActivity() {

    private var arrDay = arrayListOf<Date>()
    private val arr2D = arrayListOf<MutableList<Date>>()
    var daysLetter = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu")
    var monthLetter = arrayOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")

    val even = emptyArray<String>()
    val odd = emptyArray<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val s = spinner
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, monthLetter)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        s.adapter = adapter

        button12.setOnClickListener {
            arr2D.clear()
            arrDay.clear()
            val calendar = Calendar.getInstance()
            val dateString = "01-${monthLetter.indexOf(spinner.selectedItem.toString())+1}-${editText8.text}"
            calendar.time = dateString.simpleToDate()

            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            val dateMax = calendar.time

            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
            val dateMin = calendar.time

            var i = dateMin.getD()

            while (i <= dateMax.getD()) {
                val dateToAdd = i.toString()+"-"+(monthLetter.indexOf(spinner.selectedItem.toString())+1)+"-"+editText8.text.toString()
                calendar.time = dateToAdd.simpleToDate()
                arrDay.add(calendar.time)
                i++
            }
            renderCalendar()
        }
    }

    private fun renderCalendar () {
        val calendar = Calendar.getInstance()
        calendar.time = arrDay[0]
        if (calendar.get(Calendar.DAY_OF_WEEK) != 1 && calendar.get(Calendar.DAY_OF_WEEK) != 2) {
            addDateEnd(calendar.get(Calendar.DAY_OF_WEEK)-2, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                arrDay.add(0, date)
            }
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            addDateEnd(6, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                arrDay.add(0, date)
            }
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {

        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
            addDateEnd(1, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                arrDay.add(0, date)
            }
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
            addDateEnd(2, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                arrDay.add(0, date)
            }
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
            addDateEnd(3, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                arrDay.add(0, date)
            }
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
            addDateEnd(4, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                arrDay.add(0, date)
            }
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
            addDateEnd(5, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                arrDay.add(0, date)
            }
        }

        calendar.time = arrDay[arrDay.size-1]
        when {
            calendar.get(Calendar.DAY_OF_WEEK) == 1 -> {
            }
            calendar.get(Calendar.DAY_OF_WEEK) == 2 -> {
                addDateStart(6, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                    arrDay.add(date)
                }
            }
            calendar.get(Calendar.DAY_OF_WEEK) == 3 -> {
                addDateStart(5, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                    arrDay.add(date)
                }
            }
            calendar.get(Calendar.DAY_OF_WEEK) == 4 -> {
                addDateStart(4, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                    arrDay.add(date)
                }
            }
            calendar.get(Calendar.DAY_OF_WEEK) == 5 -> {
                addDateStart(3, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                    arrDay.add(date)
                }
            }
            calendar.get(Calendar.DAY_OF_WEEK) == 6 -> {
                addDateStart(2, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                    arrDay.add(date)
                }
            }
            calendar.get(Calendar.DAY_OF_WEEK) == 7 -> {
                addDateStart(1, calendar.get(Calendar.MONTH)).forEachIndexed { index, date ->
                    arrDay.add(date)
                }
            }
            else -> {

            }
        }
        var i = 0
        arr2D.add(arrayListOf())
        arrDay.forEachIndexed { index, date ->
            calendar.time = date
            if ((index+1)%7 != 0) {
                arr2D[i].add(date)
            } else {
                arr2D[i].add(date)
                i++
                arr2D.add(arrayListOf())
            }
        }

        var x = 0
        val lineParentInit = LinearLayout(this)
        lineParentInit.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lineParentInit.orientation = LinearLayout.HORIZONTAL
        while (x < daysLetter.size) {

            val lineChild = LinearLayout(this)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(2, 2, 2, 2)
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
            lineChild.setPadding(5, 5, 5, 5)

            val text2 = TextView(this)
            text2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            text2.textSize = 12F
            text2.text = daysLetter[x]
            text2.gravity = Gravity.CENTER
            lineChild.addView(text2)

            lineParentInit.addView(lineChild)

            x++
        }
        LinearLayouts.addView(lineParentInit)

        arr2D.forEachIndexed { index, mutableList ->
            Log.e("mutableList", mutableList.toString())
            val lineParent = LinearLayout(this)
            lineParent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lineParent.orientation = LinearLayout.HORIZONTAL

            mutableList.forEach {
                val calendar = Calendar.getInstance()
                calendar.time = it
                Log.e("mut", calendar.time.toString())

                val lineChild = LinearLayout(this)
                val params = LinearLayout.LayoutParams(75, 75)
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
                text2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                text2.textSize = 12F
                text2.gravity = Gravity.CENTER
                text2.text = it.getD().toString()
                lineChild.addView(text2)

                lineParent.addView(lineChild)
            }

            LinearLayoutss.addView(lineParent)
        }
    }

    private fun addDateEnd(length: Int, month: Int): MutableList<Date> {
        val arrDay = arrayListOf<Date>()
        val calendar = Calendar.getInstance()
        val dateString = "01-"+(month)+"-"+editText8.text.toString()
        calendar.time = dateString.simpleToDate()

        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val dateMax = calendar.time

        var i = dateMax.getD()

        while (i > dateMax.getD()-length) {
            val dateToAdd = i.toString()+"-"+(month)+"-"+editText8.text.toString()
            calendar.time = dateToAdd.simpleToDate()
            arrDay.add(calendar.time)
            i--
        }

        return arrDay
    }

    private fun addDateStart(length: Int, month: Int): MutableList<Date> {
        val arrDay = arrayListOf<Date>()
        val calendar = Calendar.getInstance()
        val dateString = "01-"+(month)+"-"+editText8.text.toString()
        calendar.time = dateString.simpleToDate()

        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
        val dateMin = calendar.time

        var i = dateMin.getD()

        while (i < dateMin.getD()+length) {
            val dateToAdd = i.toString()+"-"+(monthLetter.indexOf(spinner.selectedItem.toString())+1)+"-"+editText8.text.toString()
            calendar.time = dateToAdd.simpleToDate()
            arrDay.add(calendar.time)
            i++
        }

        return arrDay
    }

    private fun String.simpleToDate(): Date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(this)
    private fun Date.getD(): Int = SimpleDateFormat("dd", Locale.getDefault()).format(this).toInt()

}
