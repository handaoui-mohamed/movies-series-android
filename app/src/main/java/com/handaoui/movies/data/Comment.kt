package com.handaoui.movies.data

import java.util.*

data class Comment(
        val author: String,
        val content: String,
        val rating: Float = -1f,
        val date: String = "",
        val type: String = "",
        val id: Int = 0
)