package com.handaoui.movies.daos

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.handaoui.movies.Config
import com.handaoui.movies.data.Comment
import com.handaoui.movies.data.Movie
import com.handaoui.movies.data.Person

object Db {
    @Database(entities = [(Comment::class), (Person::class), (Movie::class)], version = 1)
    abstract class MovieDB : RoomDatabase() {
        abstract fun movieDao(): MovieDao
        abstract fun personDao(): PersonDao
        abstract fun commentDao(): CommentDao
    }

    private var instance: MovieDB? = null

    fun getInstance(context: Context): MovieDB? {
        if (instance == null) {
            instance = Room.databaseBuilder(context.applicationContext, MovieDB::class.java, Config.dbName).build()
        }
        return instance
    }

    fun destroyInstance() {
        instance = null
    }
}