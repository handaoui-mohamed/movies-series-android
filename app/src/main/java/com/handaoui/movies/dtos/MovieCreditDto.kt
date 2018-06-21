package com.handaoui.movies.dtos

import com.handaoui.movies.data.Movie


class MovieCreditDto (
    val cast:ArrayList<Movie> = ArrayList(),
    val crew:ArrayList<Movie> = ArrayList()
)