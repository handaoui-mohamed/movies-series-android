package com.handaoui.movies.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.handaoui.movies.data.Person
import com.handaoui.movies.data.PersonMovie

@Dao
interface PersonMovieDao {

    @Query("SELECT p.id, p.name, p.profile_path, pm.job job FROM person_movie pm, person p WHERE movieId = :movieId and personId = p.id")
    fun getAllCredits(movieId: Int): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(personMovie: PersonMovie)

    @Query("DELETE FROM person_movie WHERE movieId = :movieId")
    fun delete(movieId: Int)
}