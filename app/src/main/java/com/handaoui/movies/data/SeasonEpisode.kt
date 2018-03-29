package com.handaoui.movies.data

import java.util.*

data class SeasonEpisode(
//        val season: SeriesSeason,
        val diffusion: String,
        val directors: ArrayList<Person> = ArrayList(),
        val date: Date = Date(),
        val trailerUrl: String = "",
        var comments: ArrayList<Comment> = ArrayList()
)