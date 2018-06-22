package com.handaoui.movies.daos

import android.arch.persistence.room.*
import com.handaoui.movies.data.Movie
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE movie.id = :movieId")
    fun getMovie(movieId: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)
}