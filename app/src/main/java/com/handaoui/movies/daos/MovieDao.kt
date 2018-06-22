package com.handaoui.movies.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.handaoui.movies.data.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flowable<List<Movie>>

    @Insert
    fun insert(movie: Movie)
}