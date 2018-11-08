package com.dev.adi.collectapps

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import kotlinx.android.synthetic.main.activity_multi_calendar.*
import java.text.SimpleDateFormat
import java.util.*



class MultiCalendarActivity : AppCompatActivity() {

    private val arrDateMasehi = arrayListOf<Date>()
    private val arrDataDate = arrayListOf<MutableList<Date>>()

    private val listDay = arrayOf("Ahad", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu")
    private val listMonth = arrayOf("", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
    private val listJavaneseDay = arrayOf("Legi", "Pahing", "Pon", "Wage", "Kliwon")
//    private val listJavaneseMonth = arrayOf("Sura", "Sapar", "Mulud", "Bakda Mulud", "Jumadilawal", "Jumadilakir", "Rejeb", "Ruwah", "Pasa", "Sawwal", "Sela", "Besar")

    private var year = 2018
    private var month = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_calendar)

        val dateNow = Calendar.getInstance()
        year = dateNow.time.getYY()
        month = dateNow.time.getMM()
        initial(month)
        e("index", month.toString() )
        bt_next.setOnClickListener {
            if (month + 1 <= 12) {
                month += 1
                bt_next.visibility = View.GONE
            } else {
                bt_next.visibility = View.GONE
                return@setOnClickListener
            }
            initial(month)
        }

        bt_prev.setOnClickListener {
            if (month - 1 > -1) {
                month -= 1
            } else {
                return@setOnClickListener
            }
            initial(month)
        }
    }

    private fun initial (month : Int) {
        line_parent.removeAllViews()
        arrDataDate.clear()
        arrDateMasehi.clear()
        arrDateMasehi.addAll(getDateAll(month))
        val calendar = Calendar.getInstance()

        calendar.time = arrDateMasehi.first()
        addDateToStart(calendar.get(Calendar.DAY_OF_WEEK)-1, calendar.get(Calendar.MONTH), year).forEachIndexed { _, date ->
            arrDateMasehi.add(0, date)
        }

        calendar.time = arrDateMasehi.last()
        addDateToEnd(7-calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.MONTH), year).forEachIndexed { _, date ->
            arrDateMasehi.add(date)
        }
        arrDataDate.addAll(generateArrDate(arrDateMasehi))
        renderCalendar(month)
    }


    private fun renderCalendar(month : Int) {
        e("month", month.toString())
        val calendar = Calendar.getInstance()

        bt_prev.visibility = View.VISIBLE
        bt_next.visibility = View.VISIBLE

        lable.text = listMonth[month]
        val lineParentInit = LinearLayout(this)
        lineParentInit.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lineParentInit.orientation = LinearLayout.HORIZONTAL

        listDay.forEachIndexed { day, s ->
            val lineChild = LinearLayout(this)
            val params = LinearLayout.LayoutParams(140, 140)
            params.setMargins(2,2,2,2)
            lineChild.layoutParams = params
            lineChild.orientation = LinearLayout.VERTICAL
            lineChild.gravity = Gravity.CENTER_VERTICAL
            val border = GradientDrawable()
            border.setColor(-0x1) //white background
            border.setStroke(1, -0x1000000) //black border with full opacity
            lineChild.background = border
            lineChild.setPadding(5,5,5,5)

            val text2 = TextView(this)
            text2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            text2.textSize = 12F
            text2.gravity = Gravity.CENTER
            text2.text = s[0].toString()+s[1]+s[2] + "\n" + toArab(day, month)
            lineChild.addView(text2)
            lineParentInit.addView(lineChild)
        }

        line_parent.addView(lineParentInit)

        arrDataDate.forEachIndexed { i, mutableList ->
            val lineParent = LinearLayout(this)
            lineParent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lineParent.orientation = LinearLayout.HORIZONTAL

            mutableList.forEachIndexed { index, date ->
                calendar.time = date
                val lineChild = LinearLayout(this)
                val params = LinearLayout.LayoutParams(140, 140)
                params.setMargins(2, 2, 2, 2)
                lineChild.layoutParams = params
                lineChild.orientation = LinearLayout.VERTICAL
                lineChild.gravity = Gravity.CENTER_VERTICAL
                if (date.getMM() == month) {
                    val border = GradientDrawable()
                    border.setColor(-0x1) //white background
                    border.setStroke(1, -0x1000000) //black border with full opacity
                    lineChild.background = border
                }

                val text3 = TextView(this)
                text3.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                text3.textSize = 10F
                text3.gravity = Gravity.CENTER
                text3.text = daysArr(date.getDD(), month).toString()
                lineChild.addView(text3)

                val text2 = TextView(this)
                text2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                text2.textSize = 18F
                text2.gravity = Gravity.CENTER
                text2.text = date.getDD().toString()

                when (index) {
                    0 -> text2.setTextColor(Color.parseColor("#FF0000"))
                    5 -> text2.setTextColor(Color.parseColor("#008000"))
                }
                lineChild.addView(text2)

                val text4 = TextView(this)
                text4.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                text4.textSize = 10F
                text4.gravity = Gravity.CENTER
                text4.text = javaneseDay(date)
                lineChild.addView(text4)
                lineParent.addView(lineChild)
            }

            line_parent.addView(lineParent)
        }
    }

    private fun generateArrDate(arrDate: MutableList<Date>): MutableList<MutableList<Date>> {
        val tempArr = arrayListOf<MutableList<Date>>()
        var i = 0
        val calendar = Calendar.getInstance()
        tempArr.add(arrayListOf())
        arrDate.forEachIndexed { index, date ->
            calendar.time = date
            if ((index+1)%7 != 0) {
                tempArr[i].add(date)
            } else {
                tempArr[i].add(date)
                i++
                tempArr.add(arrayListOf())
            }
        }

        return tempArr
    }

    private fun getDateAll(month: Int): MutableList<Date> {
        val arrTemp = arrayListOf<Date>()
        val calendar = Calendar.getInstance()
        val dateString = "01-"+month+"-"+ year.toString()
        calendar.time = dateString.simpleToDate()

        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val dateMax = calendar.time

        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
        val dateMin = calendar.time

        var i = dateMin.getDD()
        while (i <= dateMax.getDD()) {
            val dateToAdd = i.toString()+"-"+ month +"-"+ year.toString()
            calendar.time = dateToAdd.simpleToDate()
            arrTemp.add(calendar.time)
            i++
        }

        return arrTemp
    }

    private fun addDateToStart(length: Int, month: Int, year : Int): MutableList<Date> {
        val tempArr = arrayListOf<Date>()
        val calendar = Calendar.getInstance()
        val dateString = "01-$month-$year"
        calendar.time = dateString.simpleToDate()

        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val dateMax = calendar.time

        var i = dateMax.getDD()

        while (i > dateMax.getDD()-length) {
            val dateToAdd = i.toString()+"-"+month+"-"+ year
            calendar.time = dateToAdd.simpleToDate()
            tempArr.add(calendar.time)
            i--
        }

        return tempArr
    }

    private fun addDateToEnd(length: Int, month: Int, year : Int): MutableList<Date> {
        val tempArr = arrayListOf<Date>()
        val calendar = Calendar.getInstance()
        val dateString = "01-$month-$year"
        calendar.time = dateString.simpleToDate()

        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
        val dateMin = calendar.time

        var i = dateMin.getDD()

        while (i < dateMin.getDD()+length) {
            val dateToAdd = i.toString()+"-"+month+"-"+ year
            calendar.time = dateToAdd.simpleToDate()
            tempArr.add(calendar.time)
            i++
        }

        return tempArr
    }

    fun daysArr (day : Int, months : Int) : Int {
        val ar = Locale("ar")
        val gCal = GregorianCalendar(2018, months, day)
        val uCal = UmmalquraCalendar()
        uCal.time = gCal.time
        return uCal.get(Calendar.DAY_OF_MONTH)
    }

    private fun toArab (day : Int, months : Int) : String {
        val ar = Locale("ar")
        val gCal = GregorianCalendar(2018, months, day)
        val uCal = UmmalquraCalendar()
        uCal.time = gCal.time
        return uCal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, ar)
    }

    private fun javaneseDay (date: Date): String {
        val startCalendar = Calendar.getInstance()
        startCalendar.set(2018, 0, 1)
        val endCalendar = Calendar.getInstance()
        endCalendar.time = date
        val diffInDays = ((endCalendar.timeInMillis - startCalendar.timeInMillis)
                / (1000 * 60 * 60 * 24)).toInt()
        return listJavaneseDay[0]
    }


    private fun String.simpleToDate(): Date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(this)
    private fun Date.getDD(): Int = SimpleDateFormat("dd", Locale.getDefault()).format(this).toInt()
    private fun Date.getMM(): Int = SimpleDateFormat("MM", Locale.getDefault()).format(this).toInt()
    private fun Int.getMMMM(): String = SimpleDateFormat("MMMM", Locale.getDefault()).format(this).toString()
    private fun Date.getYY(): Int = SimpleDateFormat("yyyy", Locale.getDefault()).format(this).toInt()
}
