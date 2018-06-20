package com.handaoui.movies.services

import com.handaoui.movies.Config
import okhttp3.Interceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient

object Api {
    private val clientInterceptor = Interceptor { chain ->
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", Config.apiKey).build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
            .addNetworkInterceptor(clientInterceptor)
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(Config.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    val movieService = retrofit.create(MovieService::class.java)
}