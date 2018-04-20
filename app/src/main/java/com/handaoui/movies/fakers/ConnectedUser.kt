package com.handaoui.movies.fakers

import com.handaoui.movies.data.UserProfile

object User {
    val profile = UserProfile("Jone Doe").apply {
        addMovie(1)
        addSeries(0)
        addSeries(2)
        addProjectionRoom(0)
        addProjectionRoom(2)
    }
}