package com.handaoui.movies.data

data class Person(
        val id: Int,
        val name: String,
        val picture: Int,
        val bio: String,
        val filmography: ArrayList<Movie> = ArrayList(),
        var comments: ArrayList<Comment> = ArrayList()
)