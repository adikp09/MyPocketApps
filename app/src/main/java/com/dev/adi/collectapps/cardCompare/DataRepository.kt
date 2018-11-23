package com.dev.adi.collectapps.cardCompare

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {
    fun create(): Services {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://simple-cointoss.herokuapp.com/cards/")
                .build()
        return retrofit.create(Services::class.java)
    }
}