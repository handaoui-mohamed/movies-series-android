package com.handaoui.movies

object Config {
    private const val imageBaseUrl = "https://image.tmdb.org/t/p/"
    const val baseUrl = "https://api.themoviedb.org/3/"
    const val imagePreviewUrl = imageBaseUrl + "w300"
    const val imageUrl = imageBaseUrl + "w780"
    const val apiKey = "16df3c616f7d981d0285dc3af9d7b732"
    const val dbName: String = "movie.db"
}