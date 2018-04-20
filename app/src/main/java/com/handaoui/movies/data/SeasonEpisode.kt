package com.handaoui.movies.data

import java.util.*

data class SeasonEpisode(
//        val season: SeriesSeason,
        val id : Int,
        val diffusion: String,
        val directors: ArrayList<Person> = ArrayList(),
        val actors: ArrayList<Person> = ArrayList(),
        val date: String = Date().toString(),
        val trailerUrl: String = "",
        var comments: ArrayList<Comment> = ArrayList()
)