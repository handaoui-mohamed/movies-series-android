package com.handaoui.movies.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handaoui.movies.R
import com.handaoui.movies.adapters.ProjectionRoomsAdapter


class ProjectionRoomsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_projection_rooms, container, false)
        this.createMoviesPreview(rootView)
        return rootView
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 250).toInt()
    }


    private fun createMoviesPreview(rootView: View) {
        val recyclerView: RecyclerView = rootView.findViewById(R.id.projectionRoomsContainer)
        recyclerView.layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))

        var type = ""
        if(arguments != null){
            type = arguments!!.getString("type", "")
        }

        val projectionRoomsAdapter = ProjectionRoomsAdapter(
                rootView.context,
                ArrayList()
        )
        recyclerView.adapter = projectionRoomsAdapter
    }
}
