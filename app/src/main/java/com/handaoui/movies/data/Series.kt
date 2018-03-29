package com.handaoui.movies.data

import java.util.*
import kotlin.collections.ArrayList

data class Series(
        val title: String,
        val cover: Int,
        val description: String,
        val relatedSeries: ArrayList<Series> = ArrayList(),
        val date: Date = Date(),
        val tags: ArrayList<String> = ArrayList(),
        val trailerUrl: String,
        var comments: ArrayList<Comment> = ArrayList()
)