package com.handaoui.movies.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
            entity = Movie::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("movieId"),
            onDelete = CASCADE
    )
])
data class Comment(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val author: String,
        val content: String,
        val rating: Float = -1f,
        val date: String = "",
        val type: String = "",
        val movieId: Int = 0
)