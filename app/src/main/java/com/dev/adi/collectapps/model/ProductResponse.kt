package com.dev.adi.collectapps.model
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2018 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


class ProductResponse (

		@SerializedName("status") val status : String,
		@SerializedName("promo_due") val promo_due : String,
		@SerializedName("tag_page") val tag_page : String,
		@SerializedName("banner") val banner : String,
		@SerializedName("products") val products : List<Products>,
		@SerializedName("message") val message : String,
		@SerializedName("facets") val facets : List<String>,
		@SerializedName("labels") val labels : String,
		@SerializedName("related_related_keywordswords") val related_related_keywordswords : String,
		@SerializedName("recommended_products") val recommended_products : List<String>,
		@SerializedName("product_deeplink") val product_deeplink : String
)