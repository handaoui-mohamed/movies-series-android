package com.handaoui.movies.services

import com.handaoui.movies.data.Movie
import com.handaoui.movies.dtos.CommentsDto
import com.handaoui.movies.dtos.CreditsDto
import com.handaoui.movies.dtos.MoviesDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("movie/now_playing")
    fun getPlayingMovies(@Query("page") page: Int): Call<MoviesDto>

    @GET("movie/popular")
    fun getAllMovies(@Query("page") page: Int): Call<MoviesDto>

    @GET("movie/{movieId}")
    fun getMovie(@Path("movieId") movieId: Int): Call<Movie>

    @GET("movie/{movieId}/similar")
    fun getSimilarMovies(@Path("movieId") movieId: Int, @Query("page") page: Int): Call<MoviesDto>

    @GET("movie/{movieId}/reviews")
    fun getMovieReviews(@Path("movieId") movieId: Int, @Query("page") page: Int): Call<CommentsDto>

    @GET("movie/{movieId}/credits")
    fun getMovieCredits(@Path("movieId") movieId: Int): Call<CreditsDto>
}