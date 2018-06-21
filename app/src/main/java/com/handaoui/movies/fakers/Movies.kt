package com.handaoui.movies.fakers

import com.handaoui.movies.data.Movie
import kotlin.collections.ArrayList

object Movies {
    private var projectRooms = ProjectionRooms.list
    private var persons = Persons.list

    var list: ArrayList<Movie> = ArrayList()

    fun getProjectedMovies(): ArrayList<Movie> {
        val filtered = ArrayList<Movie>()
        list.forEach { movie -> if (movie.projectRoom !== null) filtered.add(movie) }
        return filtered
    }

    fun getRelatedMovies(movieId: Int): ArrayList<Movie> {
        val movie = getMovieById(movieId)
        val filtered = ArrayList<Movie>()

        // TODO: replace isNotEmpty() with a fixed number like 2
//        list.forEach { mv -> if (mv.id != movie!!.id && mv.genres.intersect(movie!!.genres).isNotEmpty()) filtered.add(mv) }

        return filtered
    }

    fun getListFromIds(ids: ArrayList<Int>): ArrayList<Movie> {
        val filtered = ArrayList<Movie>()
        list.forEach { movie -> if (ids.contains(movie.id)) filtered.add(movie) }
        return filtered
    }

    fun getMovieById(id: Int): Movie? = list.find { movie -> movie.id == id }
}