package com.handaoui.movies.dtos

import com.handaoui.movies.data.Series


data class SeriesDto (
    val page:Int = 1,
    val results:ArrayList<Series> = ArrayList(),
    val total_pages:Int = 1,
    val total_results:Int = 1
)