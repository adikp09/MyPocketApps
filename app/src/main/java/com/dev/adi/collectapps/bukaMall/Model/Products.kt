package com.dev.adi.collectapps.bukaMall.Model

data class Products (

        val price : Int,
        val category : String,
        val seller_name : String,
        val seller_avatar : String,
        val seller_level : String,
        val seller_positive_feedback : Int,
        val seller_term_condition : String,
        val id : String,
        val name : String,
        val city : String,
        val images : List<String>,
        val desc : String,
        val condition : String,
        val stock : Int,
        val view_count : Int,
        val interest_count : Int,
        val sold_count : Int
)