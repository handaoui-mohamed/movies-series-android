package com.handaoui.movies.fakers

import android.content.res.Resources
import com.handaoui.movies.R
import com.handaoui.movies.data.Movie

object Movies {
    private var projectRooms = ProjectionRooms.list
    private var values = Resources.getSystem()
//    var string = values.getString(R.string.hello_blank_fragment)

    var list: ArrayList<Movie> = arrayListOf(
            Movie("Harry Potter", R.drawable.harry,projectRoom = projectRooms[0]),
            Movie("The Shawshank", R.drawable.shawshank),
            Movie("The beast", R.drawable.beast, projectRoom = projectRooms[2])
    )
}