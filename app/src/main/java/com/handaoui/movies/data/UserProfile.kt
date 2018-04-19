package com.handaoui.movies.data


class UserProfile(val name:String) {
    private val favoriteMovies = ArrayList<Int>()
    private val favoriteSeries = ArrayList<Int>()

    fun isMovieFavored(movieId: Int) = favoriteMovies.contains(movieId)

    fun addMovie(movieId: Int){
        if(!isMovieFavored(movieId)) favoriteMovies.add(movieId)
    }

    fun removeMovie(movieId: Int){
        if(isMovieFavored(movieId)) favoriteMovies.remove(movieId)
    }

    fun isSeriesFavored(seriesId: Int) = favoriteMovies.contains(seriesId)

    fun addSeries(seriesId: Int){
        if(!isSeriesFavored(seriesId)) favoriteSeries.add(seriesId)
    }

    fun removeSeries(seriesId: Int){
        if(isSeriesFavored(seriesId)) favoriteSeries.remove(seriesId)
    }
}