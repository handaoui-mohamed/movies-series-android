package com.handaoui.movies.data

data class Person(
        val id: Int,
        val name: String,
        val biography: String = "",
        val birthday: String = "",
        val profile_path: String,
        val job: String = "actor"
)