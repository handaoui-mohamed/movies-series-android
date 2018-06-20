package com.handaoui.movies.fakers

import com.handaoui.movies.data.ProjectionRoom


object ProjectionRooms {
    var list: ArrayList<ProjectionRoom> = ArrayList()

    fun getListFromIds(ids: ArrayList<Int>): ArrayList<ProjectionRoom> {
        val filtered = ArrayList<ProjectionRoom>()
        list.forEach { room -> if (ids.contains(room.id)) filtered.add(room) }
        return filtered
    }
}