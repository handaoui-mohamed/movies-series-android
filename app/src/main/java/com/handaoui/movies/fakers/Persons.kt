package com.handaoui.movies.fakers

import com.handaoui.movies.data.Person

object Persons {
    var list = ArrayList<Person>()

    fun getPersonById(personId:Int) = list.find { person -> person.id == personId }

}