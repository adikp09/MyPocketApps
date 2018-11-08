package com.dev.adi.collectapps.model
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2018 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Products (

        @SerializedName("price") val price : Int,
        @SerializedName("category_id") val category_id : Int,
        @SerializedName("category") val category : String,
        @SerializedName("category_structure") val category_structure : List<String>,
        @SerializedName("seller_username") val seller_username : String,
        @SerializedName("seller_name") val seller_name : String,
        @SerializedName("seller_id") val seller_id : Int,
        @SerializedName("seller_avatar") val seller_avatar : String,
        @SerializedName("seller_level") val seller_level : String,
        @SerializedName("seller_level_badge_url") val seller_level_badge_url : String,
        @SerializedName("seller_delivery_time") val seller_delivery_time : String,
        @SerializedName("seller_positive_feedback") val seller_positive_feedback : Int,
        @SerializedName("seller_negative_feedback") val seller_negative_feedback : Int,
        @SerializedName("seller_term_condition") val seller_term_condition : String,
        @SerializedName("seller_alert") val seller_alert : String,
        @SerializedName("for_sale") val for_sale : Boolean,
        @SerializedName("state_description") val state_description : List<String>,
        @SerializedName("premium_account") val premium_account : Boolean,
        @SerializedName("brand") val brand : Boolean,
        @SerializedName("top_merchant") val top_merchant : Boolean,
        @SerializedName("waiting_payment") val waiting_payment : Int,
        @SerializedName("sold_count") val sold_count : Int,
        @SerializedName("favorited") val favorited : Boolean,
        @SerializedName("force_insurance") val force_insurance : Boolean,
        @SerializedName("free_shipping_coverage") val free_shipping_coverage : List<String>,
        @SerializedName("video_url") val video_url : String,
        @SerializedName("assurance") val assurance : Boolean,
        @SerializedName("labels") val labels : List<Labels>,
        @SerializedName("id") val id : String,
        @SerializedName("url") val url : String,
        @SerializedName("name") val name : String,
        @SerializedName("active") val active : Boolean,
        @SerializedName("city") val city : String,
        @SerializedName("province") val province : String,
        @SerializedName("weight") val weight : Int,
        @SerializedName("image_ids") val image_ids : List<Int>,
        @SerializedName("images") val images : List<String>,
        @SerializedName("small_images") val small_images : List<String>,
        @SerializedName("desc") val desc : String,
        @SerializedName("condition") val condition : String,
        @SerializedName("stock") val stock : Int,
        @SerializedName("created_at") val created_at : String,
        @SerializedName("updated_at") val updated_at : String,
        @SerializedName("product_sin") val product_sin : List<String>,
        @SerializedName("rating") val rating : Rating,
        @SerializedName("current_variant_name") val current_variant_name : String,
        @SerializedName("alternative_image") val alternative_image : String,
        @SerializedName("min_quantity") val min_quantity : Int,
        @SerializedName("max_quantity") val max_quantity : Int,
        @SerializedName("has_bundling") val has_bundling : Boolean,
        @SerializedName("rush_delivery") val rush_delivery : Boolean,
        @SerializedName("on_bundling") val on_bundling : Boolean,
        @SerializedName("installment") val installment : List<Installment>,
        @SerializedName("min_installment_price") val min_installment_price : String,
        @SerializedName("courier") val courier : List<String>,
        @SerializedName("on_daily_deal") val on_daily_deal : Boolean,
        @SerializedName("negotiation") val negotiation : Negotiation,
        @SerializedName("sla_display") val sla_display : Int,
        @SerializedName("sla_type") val sla_type : String,
        @SerializedName("sla_display_raw") val sla_display_raw : String,
        @SerializedName("sla_type_raw") val sla_type_raw : String,
        @SerializedName("interest_count") val interest_count : Int,
        @SerializedName("last_relist_at") val last_relist_at : String,
        @SerializedName("view_count") val view_count : Int
)