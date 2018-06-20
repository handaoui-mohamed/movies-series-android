package com.handaoui.movies.data

import java.util.*
import kotlin.collections.ArrayList

data class Series(
        val id: Int,
        val name: String,
        val poster_path: String,
        val overview: String,
        val first_air_date: String,
        val vote_average: Float = 0f
//        val seasons: ArrayList<SeriesSeason> = ArrayList()
)