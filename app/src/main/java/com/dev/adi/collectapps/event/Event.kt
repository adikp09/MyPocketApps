package com.dev.adi.collectapps.event

import java.io.Serializable

data class Event (
        val subject: String,
        val location: String,
        val eventForm : String,
        val eventTo : String,
        val timezone : String
) : Serializable