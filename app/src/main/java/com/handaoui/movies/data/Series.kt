package com.handaoui.movies.data

import java.util.*
import kotlin.collections.ArrayList

data class Series(
        val id: Int,
        val title: String,
        val cover: Int,
        val description: String,
        val relatedSeries: ArrayList<Series> = ArrayList(),
        val date: String = Date().toString(),
        val tags: ArrayList<String> = ArrayList(),
        val trailerUrl: String = "",
        var comments: ArrayList<Comment> = ArrayList(),
        var rating: Float = 0f,
        val genre: List<String> = ArrayList(),
        val seasons: ArrayList<SeriesSeason> = ArrayList()
)