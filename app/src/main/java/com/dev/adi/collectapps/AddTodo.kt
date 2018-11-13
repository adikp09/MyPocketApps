package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.dev.adi.collectapps.helpermodul.Storage
import com.dev.adi.collectapps.task.Task
import com.dev.adi.collectapps.task.TaskAdapter

class AddTodo : AppCompatActivity() {

    val adapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val description = findViewById(R.id.task_description) as EditText
        val submit = findViewById(R.id.submit) as Button

        submit.setOnClickListener {
            if (description.text?.toString().isNullOrBlank()) {
                description.error = "Please enter a description"
            } else {
                val task = Task(description.text.toString())
                adapter.addTask(task)
            }

            val recyclerView = findViewById<RecyclerView>(R.id.task_list)
            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        val tasks = Storage.readData(this)
        if (tasks != null && (adapter.tasks.isEmpty())) {
            adapter.tasks = tasks
        }
    }

    override fun onPause() {
        super.onPause()
        Storage.writeData(this, adapter.tasks)
    }
}
