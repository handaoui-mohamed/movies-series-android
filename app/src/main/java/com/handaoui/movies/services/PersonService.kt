package com.handaoui.movies.services

import com.handaoui.movies.data.Person
import com.handaoui.movies.dtos.MovieCreditDto
import com.handaoui.movies.dtos.PersonsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PersonService {

    @GET("person/popular")
    fun getAllPersons(@Query("page") page: Int): Call<PersonsDto>

    @GET("person/{personId}")
    fun getPerson(@Path("personId") personId: Int): Call<Person>

    @GET("person/{personId}/movie_credits")
    fun getMovieCredits(@Path("personId") personId: Int): Call<MovieCreditDto>


    @GET("person/{personId}/tv_credits")
    fun getSeriesCredits(@Path("personId") personId: Int): Call<PersonsDto>

}