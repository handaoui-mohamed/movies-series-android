package com.handaoui.movies.data

import java.util.*
import kotlin.collections.ArrayList

data class SeriesSeason(
//        val series: Series,
        val cover: Int,
        val description: String,
        val episodes: ArrayList<SeasonEpisode> = ArrayList(),
        val actors: ArrayList<Person> = ArrayList(),
        val directors: ArrayList<Person> = ArrayList(),
        val date: String = Date().toString(),
        val trailerUrl: String,
        var comments: ArrayList<Comment> = ArrayList()
)