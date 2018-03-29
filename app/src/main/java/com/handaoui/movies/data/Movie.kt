package com.handaoui.movies.data

import java.util.*
import kotlin.collections.ArrayList

data class Movie(
        val title: String,
        val cover: Int,
        val description: String = "",
        val actors: ArrayList<Person> = ArrayList(),
        val directors: ArrayList<Person> = ArrayList(),
        val projectRoom: ProjectionRoom? = null,
        val relatedMovies: ArrayList<Movie> = ArrayList(),
        val date: Date = Date(),
        val tags: ArrayList<String> = ArrayList(),
        // TODO: if i would to keep this, then i should remove related movies?
        val trailerUrl: String = "",
        var comments: ArrayList<Comment> = ArrayList()
)
