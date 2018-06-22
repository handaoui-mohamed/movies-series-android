package com.handaoui.movies.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity()
data class Movie(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title: String,
        val poster_path: String,
        val overview: String = "",
        val release_date: String = Date().toString(),
        var vote_average: Float = 0f
)
