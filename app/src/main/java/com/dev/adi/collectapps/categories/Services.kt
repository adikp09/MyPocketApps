package com.dev.adi.collectapps.categories

import retrofit2.Call
import retrofit2.http.GET

interface Services {
    @GET("categories.json")
    fun getCategory(): Call<CategoriesResponse>
}