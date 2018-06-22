package com.handaoui.movies.daos

import android.arch.persistence.room.*
import com.handaoui.movies.data.Person
import io.reactivex.Flowable

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun getAllPersons(): Flowable<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Person)
}