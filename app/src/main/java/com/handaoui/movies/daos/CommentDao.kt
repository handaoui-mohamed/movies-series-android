package com.handaoui.movies.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.handaoui.movies.data.Comment
import io.reactivex.Flowable

@Dao
interface CommentDao {

    @Query("SELECT * FROM comment")
    fun getMovieComments(): Flowable<List<Comment>>

    @Insert
    fun insert(comment: Comment)
}