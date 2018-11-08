package com.dev.adi.collectapps.categories

class CategoriesModel (
        val id : Int,
        val name : String,
        val children : List<CategoriesModel>
)