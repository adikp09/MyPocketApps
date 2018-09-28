package com.dev.adi.collectapps

import java.io.Serializable

data class Task(var description: String, var completed: Boolean = false) : Serializable