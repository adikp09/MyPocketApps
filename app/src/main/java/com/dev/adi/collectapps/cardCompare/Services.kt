package com.dev.adi.collectapps.cardCompare

import com.dev.adi.collectapps.cardCompare.model.RespondCard
import com.dev.adi.collectapps.cardCompare.model.RespondCompare
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("play")
    fun getCardPlay(): Call<ResponseBody>

    @GET("compare")
    fun getCardCompare(
            @Query("card_id1") id1 : Int,
            @Query("card_id2") id2 : Int
    ): Call<RespondCompare>

    @GET("show")
    fun getCardShow(): Call<ArrayList<RespondCard>>
}