package com.handaoui.movies.dtos

import com.handaoui.movies.data.Person

data class PersonsDto (
    val page:Int = 1,
    val results:ArrayList<Person> = ArrayList(),
    val total_pages:Int = 1,
    val total_results:Int = 1
)