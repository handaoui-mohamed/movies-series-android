package com.handaoui.movies.data

import java.util.*

data class Comment(
        val commentator: String,
        val content: String,
        val rating: Int = -1,
        val date: Date = Date()
)