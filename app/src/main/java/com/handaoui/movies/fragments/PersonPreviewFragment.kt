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
import com.handaoui.movies.fakers.Series


class PersonPreviewFragment : Fragment() {
    private lateinit var persons: ArrayList<Person>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_person_preview, container, false)
        this.createPersonPreview(rootView)
        val type = arguments.getString("type", "movie")
        val forActors = arguments.getString("personType", "actors") == "actors"
        val id = arguments.getInt("id", 0)

        when (type){
            "movie" -> {
                val movie = Movies.getMovieById(id)
                persons = if(forActors) movie!!.actors else movie!!.directors
            }
        }
        return rootView
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (displayMetrics.widthPixels / displayMetrics.density).toInt()
    }

    private fun createPersonPreview(rootView: View) {
        val recyclerView: RecyclerView = rootView.findViewById(R.id.personsPreview)
        recyclerView.layoutManager = GridLayoutManager(rootView.context, calculateNoOfColumns(rootView.context))
        val personPreviewAdapter = PersonPreviewAdapter(rootView.context, persons)
        recyclerView.adapter = personPreviewAdapter
    }
}
