package com.handaoui.movies.data


class UserProfile(val name:String) {
    val favoriteMovies = ArrayList<Int>()
    val favoriteSeries = ArrayList<Int>()
    val favoriteProjectionRooms = ArrayList<Int>()

    fun isProjectionRoomFavored(ProjectionRoomId: Int) = favoriteProjectionRooms.contains(ProjectionRoomId)

    fun addProjectionRoom(ProjectionRoomId: Int){
        if(!isProjectionRoomFavored(ProjectionRoomId)) favoriteProjectionRooms.add(ProjectionRoomId)
    }

    fun removeProjectionRoom(ProjectionRoomId: Int){
        if(isProjectionRoomFavored(ProjectionRoomId)) favoriteProjectionRooms.remove(ProjectionRoomId)
    }

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