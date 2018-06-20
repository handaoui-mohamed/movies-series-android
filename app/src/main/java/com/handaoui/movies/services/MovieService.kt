package com.handaoui.movies.services

import com.handaoui.movies.dtos.MoviesDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("movie/now_playing")
    fun getPlayingMovies(@Query("page") page:Int): Call<MoviesDto>

    @GET("movie")
    fun getAllMovies(@Query("page") page:Int): Call<MoviesDto>
}