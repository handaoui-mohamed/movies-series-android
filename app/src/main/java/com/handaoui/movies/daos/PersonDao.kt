package com.handaoui.movies.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.handaoui.movies.data.Person
import io.reactivex.Flowable

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun getAllPersons(): Flowable<List<Person>>

    @Insert
    fun insert(movie: Person)
}