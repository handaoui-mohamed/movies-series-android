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
import com.handaoui.movies.adapters.PersonPreviewAdapter
import com.handaoui.movies.data.Person
import com.handaoui.movies.fakers.Movies
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper


class PersonPreviewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_person_preview, container, false)
        this.createPersonPreview(rootView)
        return rootView
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (displayMetrics.widthPixels / displayMetrics.density).toInt()
    }

    private fun createPersonPreview(rootView: View) {
        val args = arguments
        val type = args.getString("type")
        val forActors = args.getString("personType") == "actors"
        val id = args.getInt("id")
        var persons = ArrayList<Person>()
        when (type) {
            "movie" -> {
                val movie = Movies.getMovieById(id)
                persons = if (forActors) movie!!.actors else movie!!.directors
            }
        }
        val recyclerView: RecyclerView = rootView.findViewById(R.id.personsPreview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        val personPreviewAdapter = PersonPreviewAdapter(context, persons)
        recyclerView.adapter = personPreviewAdapter
    }
}
