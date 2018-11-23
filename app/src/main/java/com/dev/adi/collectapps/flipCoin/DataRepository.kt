package com.dev.adi.collectapps.flipCoin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {
    fun create(): Services {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://superficial-nation.glitch.me/coin")
                .build()
        return retrofit.create(Services::class.java)
    }
}