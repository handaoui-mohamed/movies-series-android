package com.handaoui.movies.data

import java.util.*
import kotlin.collections.ArrayList

data class Movie(
        val id:Int,
        val title: String,
        val poster_path: String,
        val overview: String = "",
        val actors: ArrayList<Person> = ArrayList(),
        val directors: ArrayList<Person> = ArrayList(),
        val projectRoom: ProjectionRoom? = null,
        val release_date: String = Date().toString(),
        val tags: ArrayList<String> = ArrayList(),
        val trailerUrl: String = "",
        var comments: ArrayList<Comment> = ArrayList(),
        var vote_average: Float = 0f,
        val genres: ArrayList<String> = ArrayList()
)
