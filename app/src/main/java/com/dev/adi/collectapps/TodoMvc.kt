package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_todo_mvc.*

class TodoMvc : AppCompatActivity() {

    private val task = arrayListOf<Task>()
    private var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_mvc)

        submit.setOnClickListener {
            val text = editText8.text.toString()
            val arrayString = text.split(" ")
            when {
                arrayString[0].toUpperCase() == "CREATE" -> {
                    val leftover = text.substring(arrayString[0].length+2, text.length-1)
                    task.add(Task(leftover, index, 1))
                    index++
                }
                arrayString[0].toUpperCase() == "MOVE" -> {
                    val number = arrayString[1].toInt()
                    Log.e("number", number.toString())
                    val i: Int =
                            when {
                                arrayString[2] == "done" -> 3
                                arrayString[2] == "doing" -> 2
                                arrayString[2] == "todo" -> 1
                                else -> 0
                            }
                    if (i == 0) Toast.makeText(this, "error happend", Toast.LENGTH_SHORT).show()
                    else task[number - 1] = Task(task[number - 1].name, task[number - 1].id, i)
                }
                arrayString[0].toUpperCase() == "REMOVE" -> {
                    val number = arrayString[1].toInt()
                    task.removeAt(number-1)
                }
                else -> editText8.error = "Can't be blank"
            }
            linearLayout5.removeAllViews()
            linearLayout6.removeAllViews()
            linearLayout7.removeAllViews()
            task.forEachIndexed { index, it ->
                val text = TextView(this)
                text.textSize = 14F
                text.setOnClickListener {
                    Log.e("index", index.toString())
                    Log.e("it", it.id.toString())
                }
                text.text = it.id.toString() +". "+it.name
                when {
                    it.position == 1 -> linearLayout5.addView(text)
                    it.position == 2 -> linearLayout6.addView(text)
                    it.position == 3 -> linearLayout7.addView(text)
                }
            }
            editText8.setText("")
        }
    }

    class Task (val name: String,val id: Int,val position: Int)
}
