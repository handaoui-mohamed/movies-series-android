package com.handaoui.movies.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

@Entity(
        tableName = "person_movie",
        primaryKeys = ["movieId", "personId", "job"],
        foreignKeys = [
                ForeignKey(
                        entity = Movie::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("movieId"),
                        onDelete = ForeignKey.CASCADE
                ),
                ForeignKey(
                        entity = Person::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("personId"),
                        onDelete = ForeignKey.CASCADE
                )
        ]
)
data class PersonMovie(
        var movieId: Int = 0,
        var personId:Int = 0,
        var job: String = "actor"
)