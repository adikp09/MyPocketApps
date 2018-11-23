package com.dev.adi.collectapps.flipCoin

import retrofit2.Call
import retrofit2.http.GET

interface Services {
    @GET("/toss")
    fun getToss(): Call<CoinModel>
}