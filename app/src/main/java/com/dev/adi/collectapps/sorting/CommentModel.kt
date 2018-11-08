package com.dev.adi.collectapps.sorting

data class CommentModel(
        val body: String,
        val email: String,
        val id: Int,
        val name: String,
        val postId: Int,
        val comm : CommentModel
)