package com.handaoui.movies.dtos

import com.handaoui.movies.data.Person

data class CreditsDto (
        val cast:ArrayList<Person> = ArrayList(),
        val crew:ArrayList<Person> = ArrayList()
)