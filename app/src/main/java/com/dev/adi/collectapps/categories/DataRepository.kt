package com.dev.adi.collectapps.categories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {
    fun create(): Services {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.bukalapak.com/v2/")
                .build()
        return retrofit.create(Services::class.java)
    }
}