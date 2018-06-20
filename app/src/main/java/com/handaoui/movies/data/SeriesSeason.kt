package com.handaoui.movies.data

import java.util.*
import kotlin.collections.ArrayList

data class SeriesSeason(
        val id : Int,
        val poster_path: String,
        val name : String,
        val overview: String,
        val episodes: ArrayList<SeasonEpisode> = ArrayList(),
//        val actors: ArrayList<Person> = ArrayList(),
//        val directors: ArrayList<Person> = ArrayList(),
        val air_date: String,
        val episode_count: Int,
        val season_number: Int
//        val rating : Float = 0f
)