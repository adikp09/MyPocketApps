package com.dev.adi.collectapps.model
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2018 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Product_sku (

		@SerializedName("deal_request_state") val deal_request_state : String,
		@SerializedName("price") val price : Int,
		@SerializedName("id") val id : Int,
		@SerializedName("sku_name") val sku_name : String,
		@SerializedName("stock") val stock : Int,
		@SerializedName("variant_name") val variant_name : String,
		@SerializedName("variant") val variant : List<Variant>,
		@SerializedName("is_default") val is_default : Int,
		@SerializedName("state") val state : String,
		@SerializedName("image_ids") val image_ids : List<Int>,
		@SerializedName("images") val images : List<String>,
		@SerializedName("small_images") val small_images : List<String>,
		@SerializedName("installment") val installment : List<Installment>,
		@SerializedName("min_installment_price") val min_installment_price : String
)