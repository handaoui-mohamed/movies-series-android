package com.handaoui.movies.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.handaoui.movies.data.Movie
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

    @Insert
    fun insert(movie: Movie)
}