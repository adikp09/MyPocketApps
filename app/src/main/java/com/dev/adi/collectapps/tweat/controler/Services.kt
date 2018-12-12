package com.dev.adi.collectapps.tweat.controler

import com.dev.adi.collectapps.tweat.model.ResponseHome
import com.dev.adi.collectapps.tweat.model.ResponseSpecies
import com.dev.adi.collectapps.tweat.model.ResponseTweat
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Services {
    @GET("feeds")
    fun getHome(): Call<ResponseHome>

    @GET("ecosystem")
    fun getSpecies(): Call<ResponseSpecies>

    @FormUrlEncoded
    @POST("2/feeds")
    fun tweat(
//            @Path("species_id") id : Int,
            @Field("target_id") targetId : Int = 1
    ): Call<ResponseTweat>

//    @POST("ecosystem/{species_id}/{feed_id}/reply")
//    fun replyFeed(
//            @Path("species_id") speciesId : Int,
//            @Path("feed_id") feedId : Int,
//            @Field("content") content : String
//    ): Call<ResponseTweat>
}