package com.handaoui.movies.fakers

import com.handaoui.movies.data.Comment


object Comments {
    val movieLists = ArrayList<Comment>()

    fun getMovieComments(movieId: Int) = movieLists.filter { comment -> comment.type == "movie" && comment.id == movieId }
}