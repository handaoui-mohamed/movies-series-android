package com.handaoui.movies.dtos

import com.handaoui.movies.data.Comment

data class CommentsDto (
    val page:Int = 1,
    val results:ArrayList<Comment> = ArrayList(),
    val total_pages:Int = 1,
    val total_results:Int = 1
)