package com.handaoui.movies.fakers

import com.handaoui.movies.R
import com.handaoui.movies.data.Movie

object Movies {
    var projectRooms = ProjectionRooms.list
    var list: ArrayList<Movie> = arrayListOf(
            Movie("Harry Potter", R.drawable.harry,projectRoom = projectRooms[0]),
            Movie("The Shawshank", R.drawable.shawshank, projectRoom = projectRooms[1]),
            Movie("The beast", R.drawable.beast, projectRoom = projectRooms[2])
    )
}