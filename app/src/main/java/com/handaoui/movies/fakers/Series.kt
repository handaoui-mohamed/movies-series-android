package com.handaoui.movies.fakers

import com.handaoui.movies.R
import com.handaoui.movies.data.Comment
import com.handaoui.movies.data.Person
import com.handaoui.movies.data.Series
import com.handaoui.movies.data.SeriesSeason
import java.util.*
import kotlin.collections.ArrayList

object Series {

    var list: ArrayList<Series> = arrayListOf(
            Series(
                    id = 0,
                    title = "Game of thrones",
                    cover = R.drawable.gameofthrones,
                    description = "Nine noble families fight for control over the mythical lands of Westeros, while an ancient enemy returns after being dormant for thousands of years.",
                    rating = 9.5f,
                    genre = listOf("Action","Adventure","Drama","Fantasy","Romance"),
                    date = "17/04/2011",
                    comments = arrayListOf(
                            Comment("Adem", "Yes", 4.5f),
                            Comment("Amine", "Yey", 3.5f),
                            Comment("Achraf", "Nope", 2.2f)
                    ),
                    seasons = arrayListOf(
                            SeriesSeason(R.drawable.gameofthrones, "season one of GOT",
                                    ArrayList(),
                                    arrayListOf(Persons.list[1], Persons.list[0]),
                                    arrayListOf(Persons.list[2]),
                                    "17/04/2011",
                                    "",
                                    arrayListOf(
                                            Comment("Adem", "Yes", 4.5f),
                                            Comment("Amine", "Yey", 3.5f),
                                            Comment("Achraf", "Nope", 2.2f)
                                    )
                                    )
                    )
            ),
            Series(
                    id = 1,
                    title = "Breaking Bad",
                    cover = R.drawable.breakingbad,
                    description = "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
                    rating = 9.5f,
                    genre = listOf("Crime" , "Drama" ,"Thriller"),
                    date = "20/01/2008",
                    comments = arrayListOf(
                            Comment("Adem", "Yes", 4.5f),
                            Comment("Amine", "Yey", 3.5f),
                            Comment("Achraf", "Nope", 2.2f)
                            )
            ),
            Series(
                    id = 2,
                    title = "House of Cards ",
                    cover = R.drawable.houseofcards,
                    description = "A Congressman works with his equally conniving wife to exact revenge on the people who betrayed him.",
                    rating = 8.9f,
                    genre = listOf("Drama"),
                    date = "01/02/2013",
                    comments = arrayListOf(
                        Comment("Adem", "Yes", 4.5f),
                        Comment("Amine", "Yey", 3.5f),
                        Comment("Achraf", "Nope", 2.2f)
                    )

            ),
            Series(
                    id = 3,
                    title = "Narcos",
                    cover = R.drawable.narcos,
                    description = "A chronicled look at the criminal exploits of Colombian drug lord Pablo Escobar, as well as the many other drug kingpins who plagued the country through the years.",
                    rating = 8.9f,
                    genre = listOf("Biography" ,"Crime" ,"Drama", "Thriller"),
                    date = "01/01/2017",
                    comments = arrayListOf(
                            Comment("Adem", "Yes", 4.5f),
                            Comment("Amine", "Yey", 3.5f),
                            Comment("Achraf", "Nope", 2.2f)
                            )
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