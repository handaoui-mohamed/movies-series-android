package com.handaoui.movies.data

import java.util.*

data class SeasonEpisode(
        val id : Int,
        val name : String,
        val overview: String,
        val episode_number: Int,
        val season_number: Int,
        val air_date: String,
        val crew: ArrayList<Person> = ArrayList(),
//        val guest_stars: ArrayList<Person> = ArrayList(),
        val vote_average: Float,
        val still_path: String
//        val diffusion: String,
//        val directors: ArrayList<Person> = ArrayList(),
//        val trailerUrl: String = "",
//        var comments: ArrayList<Comment> = ArrayList()
)