package com.dev.adi.collectapps.bukaMall.Model
import com.dev.adi.collectapps.model.Rating

data class BukaMallProducts (

        val price : Int,
        val seller_name : String,
        val seller_positive_feedback : Int,
        val premium_account : Boolean,
        val brand : Boolean,
        val id : String,
        val name : String,
        val active : Boolean,
        val city : String,
        val province : String,
        val images : List<String>,
        val desc : String,
        val condition : String,
        val stock : Int,
        val rating : Rating
)