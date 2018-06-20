package com.handaoui.movies.fragments

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
import com.handaoui.movies.fakers.Series


class PersonPreviewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_person_preview, container, false)
        this.createPersonPreview(rootView)
        return rootView
    }

    private fun createPersonPreview(rootView: View) {
        val args = arguments
        val type = args!!.getString("type")
        val forActors = args.getString("personType") == "actors"
        val id = args.getInt("id")
        var persons = ArrayList<Person>()
        when (type) {
            "movie" -> {
                val movie = Movies.getMovieById(id)
                persons = if (forActors) movie!!.actors else movie!!.directors
            }
            "serie" -> {
                val serie = Series.getSeriesById(0)
//                persons = if (forActors) serie!!.seasons[id].actors else serie!!.seasons[0].directors

            }
        }
        val recyclerView: RecyclerView = rootView.findViewById(R.id.personsPreview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(rootView.context, 2, GridLayoutManager.HORIZONTAL, false)
        val personPreviewAdapter = PersonPreviewAdapter(rootView.context, persons)
        recyclerView.adapter = personPreviewAdapter
    }
}
