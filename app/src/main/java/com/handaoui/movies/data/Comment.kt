package com.handaoui.movies.data

import java.util.*

data class Comment(
        val commentator: String,
        val content: String,
        val rating: Float = -1f,
        val date: String = Date().toString(),
        val type: String = "",
        val id: Int = 0
)