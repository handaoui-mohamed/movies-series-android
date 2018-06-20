package com.handaoui.movies.dtos

import com.handaoui.movies.data.Movie

data class MoviesDto (
    val page:Int = 1,
    val results:ArrayList<Movie> = ArrayList(),
    val total_pages:Int = 1,
    val total_results:Int = 1
)