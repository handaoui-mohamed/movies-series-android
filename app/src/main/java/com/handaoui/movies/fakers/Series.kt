package com.handaoui.movies.fakers

import com.handaoui.movies.data.Series
import kotlin.collections.ArrayList

object Series {

    var list: ArrayList<Series> = ArrayList()

    fun getRelatedSeriess(serieId: Int): ArrayList<Series> {
        val serie = getSeriesById(serieId)
        val filtered = ArrayList<Series>()

        // TODO: replace isNotEmpty() with a fixed number like 2
//        list.forEach { mv -> if (mv.id != serie!!.id && mv.genre.intersect(serie.genre).isNotEmpty()) filtered.add(mv) }

        return filtered
    }

    fun getListFromIds(ids: ArrayList<Int>): ArrayList<Series> {
        val filtered = ArrayList<Series>()
        list.forEach { series -> if (ids.contains(series.id)) filtered.add(series) }
        return filtered
    }

    fun getSeriesById(id: Int): Series? = list.find { series -> series.id == id }
}