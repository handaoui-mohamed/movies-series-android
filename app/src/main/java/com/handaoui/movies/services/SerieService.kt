package com.handaoui.movies.services

import com.handaoui.movies.data.Series
import com.handaoui.movies.dtos.CommentsDto
import com.handaoui.movies.dtos.SeriesDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SerieService {
    @GET("tv/on_the_air")
    fun getPlayingSeries(@Query("page") page:Int): Call<SeriesDto>

    @GET("tv/popular")
    fun getAllSeries(@Query("page") page:Int): Call<SeriesDto>

    @GET("tv/{tv_id}")
    fun getSerie(@Path("tv_id") serieId:Int): Call<Series>

    @GET("tv/{tv_id}/similar")
    fun getSimilarSeries(@Path("tv_id") serieId:Int): Call<SeriesDto>

    @GET("tv/{tv_id}/reviews")
    fun getSerieReviews(@Path("tv_id") serieId: Int, @Query("page") page: Int): Call<CommentsDto>

}