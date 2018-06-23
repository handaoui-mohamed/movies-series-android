package com.handaoui.movies.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey


@Entity()
data class Person(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val biography: String? = "",
        val birthday: String? = "",
        val profile_path: String? = "",
        val job: String? = "actor"
)