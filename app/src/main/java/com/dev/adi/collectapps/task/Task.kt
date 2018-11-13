package com.dev.adi.collectapps.task

import java.io.Serializable

data class Task(var description: String, var completed: Boolean = false) : Serializable