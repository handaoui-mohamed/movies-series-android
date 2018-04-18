package com.handaoui.movies.fakers

import com.handaoui.movies.R
import com.handaoui.movies.data.Series
import java.util.*

object Series {

    var list: ArrayList<Series> = arrayListOf(
            Series(
                    id = 0,
                    title = "Game of thrones",
                    cover = R.drawable.gameofthrones,
                    description = "Nine noble families fight for control over the mythical lands of Westeros, while an ancient enemy returns after being dormant for thousands of years.",
                    rating = 9.5f,
                    genre = listOf("Action","Adventure","Drama","Fantasy","Romance"),
                    date = "17/04/2011"
            ),
            Series(
                    id = 1,
                    title = "Breaking Bad",
                    cover = R.drawable.breakingbad,
                    description = "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
                    rating = 9.5f,
                    genre = listOf("Crime" , "Drama" ,"Thriller"),
                    date = "20/01/2008"
            ),
            Series(
                    id = 2,
                    title = "House of Cards ",
                    cover = R.drawable.houseofcards,
                    description = "A Congressman works with his equally conniving wife to exact revenge on the people who betrayed him.",
                    rating = 8.9f,
                    genre = listOf("Drama"),
                    date = "01/02/2013"
            ),
            Series(
                    id = 3,
                    title = "Narcos",
                    cover = R.drawable.narcos,
                    description = "A chronicled look at the criminal exploits of Colombian drug lord Pablo Escobar, as well as the many other drug kingpins who plagued the country through the years.",
                    rating = 8.9f,
                    genre = listOf("Biography" ,"Crime" ,"Drama", "Thriller"),
                    date = "01/01/2017"
            )
    )
//
//    fun getProjectedMovies(): ArrayList<Movie> {
//        val filtered = ArrayList<Movie>()
//        list.forEach { movie -> if (movie.projectRoom !== null) filtered.add(movie) }
//        return filtered
//    }

    fun getSeriesById(id: Int): Series? = list.find { series -> series.id == id }
}